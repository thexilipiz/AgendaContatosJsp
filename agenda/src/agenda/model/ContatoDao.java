package agenda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import agenda.Contato;
import agenda.Endereco;

public class ContatoDao {
	Connection con;
	public String texto;

	public ContatoDao() {

	}

	public void excluirContato(int id) throws ServletException, SQLException {
		// A mesma lógica do inserirContato()
		con = new ConnectionFactory().getConnection();

		PreparedStatement removeNome = null;
		PreparedStatement removeContato = null;
		PreparedStatement removeEndereco = null;
		PreparedStatement removeEmail = null;
		PreparedStatement removeUrl = null;
		PreparedStatement removeRedeSocial = null;

		String removerNome = "DELETE FROM Nome WHERE id = ?;";
		String removerContato = "DELETE FROM Contato WHERE id = ?;";
		String removerEmail = "DELETE FROM Email WHERE id = ?;";
		String removerUrl = "DELETE FROM Url WHERE id = ?;";
		String removerRedeSocial = "DELETE FROM `Rede Social` WHERE id = ?;";
		String removerEndereco = "DELETE FROM `Endereco` WHERE id = ?;";

		try {
			removeNome = con.prepareStatement(removerNome);
			removeContato = con.prepareStatement(removerContato);
			removeEmail = con.prepareStatement(removerEmail);
			removeRedeSocial = con.prepareStatement(removerRedeSocial);
			removeUrl = con.prepareStatement(removerUrl);
			removeEndereco = con.prepareStatement(removerEndereco);
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		try {
			removeNome.setInt(1, id);
			removeEndereco.setInt(1, id);
			removeContato.setInt(1, id);
			removeEmail.setInt(1, id);
			removeRedeSocial.setInt(1, id);
			removeUrl.setInt(1, id);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		try {
			removeEndereco.executeUpdate();
			removeContato.executeUpdate();
			removeEmail.executeUpdate();
			removeRedeSocial.executeUpdate();
			removeUrl.executeUpdate();
			removeNome.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public int inserirContato(Contato contato) throws SQLException {

		int id = 0;

		// Obtém uma coneção da fánrica de conexões(classe ConnectionFactory)
		con = new ConnectionFactory().getConnection();

		// Cria um PreparedStatement para cada tabela do banco de dados
		PreparedStatement insertNome = null;
		PreparedStatement insertContato = null;
		PreparedStatement insertEndereco = null;
		PreparedStatement insertEmail = null;
		PreparedStatement insertUrl = null;
		PreparedStatement insertRedeSocial = null;

		// Cria uma string com um script sql de insert para tabela do banco de dados
		String inserirNome = "INSERT INTO Nome (Nome, `Nome Fonetico`, Sobrenome, `Sobrenome Fonetico`, `Data Nascimento`,"
				+ "Empresa, `Empresa Fonetico`) VALUES (?,?,?,?,?,?,?);";

		String inserirContato = "INSERT INTO Contato (Telefone, id) VALUES (?,?);";

		String inserirEmail = "INSERT INTO Email (`E-mail`, id) VALUES (?,?);";

		String inserirUrl = "INSERT INTO Url (Url, id) VALUES (?,?);";

		String inserirRedeSocial = "INSERT INTO `Rede Social` (`Rede Social`, id) VALUES (?,?);";

		String inserirEndereco = "INSERT INTO Endereco(Rua, Bairro, Estado, Cidade, CEP, Pais, Complemento, id) "
				+ "VALUES (?,?,?,?,?,?,?,?);";

		try {
			// Monta a query do SQL
			insertNome = con.prepareStatement(inserirNome);
			insertContato = con.prepareStatement(inserirContato);
			insertEndereco = con.prepareStatement(inserirEndereco);
			insertEmail = con.prepareStatement(inserirEmail);
			insertUrl = con.prepareStatement(inserirUrl);
			insertRedeSocial = con.prepareStatement(inserirRedeSocial);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		try {
			// Faz a preparação dos parametros para serem inseridos no banco de dados
			insertNome.setString(1, contato.getNome());
			insertNome.setString(2, contato.getNomeFonetico());
			insertNome.setString(3, contato.getSobrenome());
			insertNome.setString(4, contato.getSobrenomeFonetico());
			insertNome.setString(5, contato.getDataAniversario());
			insertNome.setString(6, contato.getEmpresa());
			insertNome.setString(7, contato.getEmpresaFonetico());

			// Executa a query
			insertNome.executeUpdate();
		} catch (SQLException e) {
			return 1;
		}

		try {
			Statement stm = con.createStatement();

			// Pega o id do último contato inserido no banco de dados
			ResultSet rs = stm.executeQuery("SELECT LAST_INSERT_ID();");

			while (rs.next()) {

				// Armazena o valor do último id na váriavel id
				id = rs.getInt("LAST_INSERT_ID()");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try {
			// Chama o método inserir lista passando a lista, o PreparedStatement e o id
			inserirListas(contato.getListaTelefone(), insertContato, id);
		} catch (SQLException e) {
			return 2;
		}

		try {
			inserirListas(contato.getListaEmail(), insertEmail, id);
		} catch (SQLException e) {
			return 3;
		}

		try {
			inserirListas(contato.getListaURL(), insertUrl, id);
		} catch (SQLException e) {
			return 4;
		}

		try {
			inserirListas(contato.getListaRedeSocial(), insertRedeSocial, id);
		} catch (SQLException e) {
			return 5;
		}

		// Faz um for para cada item da lista
		for (Endereco endereco : contato.getListaEndereco()) {
			try {
				// Faz a preparação dos parametros para serem inseridos no banco de dados
				insertEndereco.setString(1, endereco.getRua());
				insertEndereco.setString(2, endereco.getBairro());
				insertEndereco.setString(3, endereco.getEstado());
				insertEndereco.setString(4, endereco.getCidade());
				insertEndereco.setInt(5, endereco.getCEP());
				insertEndereco.setString(6, endereco.getPais());
				insertEndereco.setString(7, endereco.getComplemento());
				insertEndereco.setInt(8, id);

				// Executa a query
				insertEndereco.executeUpdate();
			} catch (SQLException e) {
				return 6;
			}
		}

		return 0;
	}

	public List<Contato> carregarContatos() throws SQLException {
		// A mesma lógica do inserirContato
		List<Contato> lista = new ArrayList<>();
		con = new ConnectionFactory().getConnection();
		Contato contat;

		String nome = "SELECT * FROM Nome ORDER BY Nome";
		String contato = "SELECT * FROM Contato WHERE id=?";
		String email = "SELECT * FROM Email WHERE id=?";
		String url = "SELECT * FROM Url WHERE id=?";
		String redeSocial = "SELECT * FROM `Rede Social` WHERE id=?";
		String endereco = "SELECT * FROM Endereco WHERE id=?";

		PreparedStatement pegarNome = null;
		PreparedStatement pegarContato = null;
		PreparedStatement pegarEmail = null;
		PreparedStatement pegarUrl = null;
		PreparedStatement pegarRedeSocial = null;
		PreparedStatement pegarEndereco = null;

		try {
			pegarNome = con.prepareStatement(nome);
			pegarContato = con.prepareStatement(contato);
			pegarEmail = con.prepareStatement(email);
			pegarUrl = con.prepareStatement(url);
			pegarRedeSocial = con.prepareStatement(redeSocial);
			pegarEndereco = con.prepareStatement(endereco);

			ResultSet rs = pegarNome.executeQuery();

			ResultSet listas = null;

			while (rs.next()) {
				contat = new Contato();

				int id = rs.getInt("id");
				System.out.println("id: " + id);
				contat.setNome(rs.getString("Nome"));
				contat.setId(id);
				contat.setNomeFonetico(rs.getString("Nome Fonetico"));
				contat.setSobrenome(rs.getString("Sobrenome"));
				contat.setSobrenomeFonetico(rs.getString("Sobrenome Fonetico"));
				contat.setEmpresa(rs.getString("Empresa"));
				contat.setEmpresaFonetico(rs.getString("Empresa Fonetico"));
				contat.setDataAniversario(rs.getString("Data Nascimento"));

				carregarListas(listas, pegarContato, contat.getListaTelefone(), id, "Telefone");
				carregarListas(listas, pegarEmail, contat.getListaEmail(), id, "E-mail");
				carregarListas(listas, pegarUrl, contat.getListaURL(), id, "Url");
				carregarListas(listas, pegarRedeSocial, contat.getListaRedeSocial(), id, "Rede Social");
				carregarListaEndereco(listas, pegarEndereco, contat.getListaEndereco(), id);

				lista.add(contat);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return lista;
	}

	public void inserirListas(ArrayList<String> lista, PreparedStatement stm, int id) throws SQLException {
		// Faz um for para cada intem da lista
		for (String componenteLista : lista) {
			// Faz a preparação dos parametros para serem inseridos no banco de dados
			stm.setString(1, componenteLista);
			stm.setInt(2, id);
			// Executa a query
			stm.executeUpdate();
		}
	}

	public void carregarListas(ResultSet rs, PreparedStatement stm, ArrayList<String> lista, int id, String label)
			throws SQLException {
		stm.setInt(1, id);
		rs = stm.executeQuery();

		while (rs.next()) {
			try {
				lista.add(rs.getString(label));
			} catch (NullPointerException e) {
				System.out.println("Nulo");
				return;
			}
		}
	}

	public void carregarListaEndereco(ResultSet rs, PreparedStatement stm, ArrayList<Endereco> lista, int id)
			throws SQLException {
		// A mesma lógica dos outrso
		stm.setInt(1, id);
		rs = stm.executeQuery();

		while (rs.next()) {
			Endereco endereco = new Endereco();

			endereco.setBairro(rs.getString("Bairro"));
			endereco.setCEP(rs.getInt("CEP"));
			endereco.setCidade(rs.getString("Cidade"));
			endereco.setPais(rs.getString("Pais"));
			endereco.setEstado(rs.getString("Estado"));
			endereco.setComplemento(rs.getString("Complemento"));
			endereco.setRua(rs.getString("Rua"));

			try {
				lista.add(endereco);
			} catch (NullPointerException e) {
				return;
			}
		}
	}

	public List<Contato> consultaPorQualquerTexto(String texto) throws SQLException {
		int id = 0;

		// Cria um objeto da classe Contato e um da classe Endereco nulos
		Contato contat = null;
		Endereco endereco = null;

		// Cria uma lista para cada informação que o contato pode ter mais de uma
		List<Contato> lista = new ArrayList<>();
		ArrayList<Endereco> listaEndereco;
		ArrayList<String> listaTelefone;
		ArrayList<String> listaEmail;
		ArrayList<String> listaURL;
		ArrayList<String> listaRedeSocial;

		// Obbtém uma conexão com o banco da fabrica de conexão
		con = new ConnectionFactory().getConnection();

		// String sql responsável por pecorrer todos os campos de todas as tablelas
		// procurando pelo texto digitado pelo cliente, caso encontrado o texto em algum
		// campo é retornado o id
		String sql = "\r\n" + "SELECT id FROM nome WHERE nome LIKE '%" + texto + "%'or id LIKE '%" + texto
				+ "%'or Sobrenome LIKE'%" + texto + "%' or Empresa LIKE '%" + texto + "%' or `Empresa Fonetico` LIKE '%"
				+ texto + "%' or `Data Nascimento` LIKE '%" + texto + "%' \r\n" + "UNION \r\n"
				+ "SELECT id FROM contato WHERE idContato LIKE '%" + texto + "%' or Telefone LIKE'%" + texto
				+ "%' or id LIKE '%" + texto + "%'\r\n" + "UNION \r\n" + "SELECT id FROM email WHERE idEmail LIKE '%"
				+ texto + "%' or `E-mail` LIKE'%" + texto + "%' or id LIKE '%" + texto + "%'\r\n" + "UNION \r\n"
				+ "SELECT id FROM endereco WHERE idEndereco LIKE '%" + texto + "%' or Rua LIKE '%" + texto
				+ "%' or Bairro LIKE '%" + texto + "%' or Estado LIKE '%" + texto + "%' or Cidade LIKE '%" + texto
				+ "%' or CEP LIKE '%" + texto + "%' or Pais LIKE '%" + texto + "%' or Complemento LIKE '%" + texto
				+ "%' or id LIKE '%" + texto + "%'\r\n" + "UNION \r\n"
				+ "SELECT id FROM `rede social` WHERE idRedeSocial LIKE '%" + texto + "%' or `Rede Social` LIKE '%"
				+ texto + "%' or id LIKE '%" + texto + "%'\r\n" + "UNION \r\n"
				+ "SELECT id FROM url WHERE idUrl LIKE '%" + texto + "%' or Url LIKE '%" + texto + "%' or id LIKE '%"
				+ texto + "%';";

		// Faz a montagem da query
		PreparedStatement st = con.prepareStatement(sql);

		try {
			// Cria um resultSet e executa a query, esse resetSet é o retorno da execução da
			// query
			ResultSet resultadoSql = st.executeQuery();

			// Enquando tiver dados o banco
			while (resultadoSql.next()) {
				// Como eu disse, a execução da string sql anterior retorn a o id, esse id é
				// armazendo em um variáveli(id)
				id = resultadoSql.getInt("id");

				// Como só temos o id do contato, essa string é responsável por fazer um join e
				// todas as
				// tabelas e pegar todas as informações do contato com aquele id
				String sqlJoin = "SELECT * FROM nome n\r\n" + "INNER JOIN endereco e ON  n.id = e.id\r\n"
						+ "INNER JOIN contato c ON n.id = c.id \r\n" + "INNER JOIN email em ON n.id = em.id\r\n"
						+ "INNER JOIN url u ON n.id = u.id \r\n" + "INNER JOIN `rede social` r ON  n.id = r.id\r\n"
						+ "WHERE n.id = " + id + ";";

				// Faz a montagem da query
				st = con.prepareStatement(sqlJoin);
				// Retorno do join
				ResultSet resultadoJoin = st.executeQuery();
				// Enquando tiver dados no banco
				while (resultadoJoin.next()) {
					// Cria um nova instância de Contato usando a variável criada lá em cima
					contat = new Contato();
					// Cria um nova instância de Endereco usando a variável criada lá em cima
					endereco = new Endereco();

					// Cria um nova instância para cada lista
					listaEndereco = new ArrayList<>();
					listaTelefone = new ArrayList<>();
					listaURL = new ArrayList<>();
					listaEmail = new ArrayList<>();
					listaRedeSocial = new ArrayList<>();

					// Seta os valores pegos do resultSet(resultado do join) no objeto contato
					// usando os métodos setters
					contat.setNome(resultadoJoin.getString("Nome"));
					contat.setId(id);
					contat.setNomeFonetico(resultadoJoin.getString("Nome Fonetico"));
					contat.setSobrenome(resultadoJoin.getString("Sobrenome"));
					contat.setSobrenomeFonetico(resultadoJoin.getString("Sobrenome Fonetico"));
					contat.setEmpresa(resultadoJoin.getString("Empresa"));
					contat.setEmpresaFonetico(resultadoJoin.getString("Empresa Fonetico"));
					contat.setDataAniversario(resultadoJoin.getString("Data Nascimento"));

					// Seta os valores pegos do resultSet(resultado do join) no objeto endereco
					// usando os métodos setters
					endereco.setBairro(resultadoJoin.getString("Bairro"));
					endereco.setCEP(resultadoJoin.getInt("CEP"));
					endereco.setCidade(resultadoJoin.getString("Cidade"));
					endereco.setPais(resultadoJoin.getString("Pais"));
					endereco.setEstado(resultadoJoin.getString("Estado"));
					endereco.setComplemento(resultadoJoin.getString("Complemento"));
					endereco.setRua(resultadoJoin.getString("Rua"));

					// Adiciona o endereco a uma lista
					listaEndereco.add(endereco);

					// Adiciona telefone(pode ter mais de um) em uma lista
					listaTelefone.add(resultadoJoin.getString("Telefone"));

					// Adiciona url(pode ter mais de um) em uma lista
					listaURL.add(resultadoJoin.getString("Url"));

					// Adiciona e-mail(pode ter mais de um) em uma lista
					listaEmail.add(resultadoJoin.getString("E-mail"));

					// Adiciona rede social(pode ter mais de um) em uma lista
					listaRedeSocial.add(resultadoJoin.getString("Rede Social"));

					// Seta as listas no objeto contato via método setters
					contat.setListaEndereco(listaEndereco);
					contat.setListaTelefone(listaTelefone);
					contat.setListaURL(listaURL);
					contat.setListaEmail(listaEmail);
					contat.setListaRedeSocial(listaRedeSocial);

					// seta o contato um uma lista que será o retorno desse método
					lista.add(contat);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// Fecha a conexão
			con.close();
			// Fecha o PreparedStatement
			st.close();
		}
		// Retorna a lista
		return lista;
	}
}
