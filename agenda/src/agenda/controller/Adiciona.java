package agenda.controller;

/* servlet para pegar dados do contato e adicionar ao banco */

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.Contato;
import agenda.Endereco;
import agenda.model.ContatoDao;

/**
 * Servlet implementation class AdicionarContatoAction
 */
@WebServlet("/Adiciona")
public class Adiciona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adiciona() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException{
    	ArrayList<String> listaTelefone= new ArrayList<>();
    	ArrayList<String> listaEmail=new ArrayList<>();
    	ArrayList<String> listaURL=new ArrayList<>();
    	ArrayList<Endereco> listaEndereco=new ArrayList<>();
    	ArrayList<String> listaRedeSocial=new ArrayList<>();

    	SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
    	
    	Date dataAniversario = null;
    	String nome = request.getParameter("nome");
    	String nomeFonetico = request.getParameter("nomefonetico");
    	String sobrenome = request.getParameter("sobrenome");
    	String sobrenomeFonetico = request.getParameter("sobrenomefonetico");
    	String empresa = request.getParameter("empresa");
    	String empresaFonetico = request.getParameter("empresafonetico");
    	
    	try {
    		dataAniversario = formato.parse(request.getParameter("datanascimento"));
    	}catch(Exception e2) {
    		throw new RuntimeException(e2);
    	}
    	
    	this.criarLista(listaTelefone, "telefones",request);
    	this.criarLista(listaEmail, "email",request);
    	this.criarLista(listaURL, "url",request);
    	this.criarLista(listaRedeSocial, "redesocial",request);
    	
    	this.criarListaEndereco(listaEndereco, request);
    	
    	Contato contato = new Contato();
    	
    	contato.setDataAniversario(dataAniversario);
    	contato.setEmpresaFonetico(empresaFonetico);
    	contato.setEmpresa(empresa);
    	contato.setNome(nome);
    	contato.setSobrenome(sobrenome);
    	contato.setNomeFonetico(nomeFonetico);
    	System.out.println(nomeFonetico);
    	contato.setSobrenomeFonetico(sobrenomeFonetico);
    	
    	contato.setListaEmail(listaEmail);
    	contato.setListaEndereco(listaEndereco);
    	contato.setListaEndereco(listaEndereco);
    	contato.setListaURL(listaURL);
    	contato.setListaTelefone(listaTelefone);
    	contato.setListaRedeSocial(listaRedeSocial);
    	
    	ContatoDao contatodao = new ContatoDao();
    	
    	int resultado = 0;
		try {
			resultado = contatodao.inserirContato(contato);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(resultado == 0) {
    		request.getRequestDispatcher("index.jsp").forward(request, response);
    	}else {
    	}
    }
    
    public void criarLista(ArrayList<String> lista, String parametro, HttpServletRequest request) {
    	for(String componentelista : request.getParameterValues(parametro)) {
    		lista.add(componentelista);
    	}
    }
    
    public void criarListaEndereco(ArrayList<Endereco> lista, HttpServletRequest request) {
    	String[] ruas = request.getParameterValues("rua");
    	String[] bairros = request.getParameterValues("bairro");
    	String[] cidades = request.getParameterValues("cidade");
    	String[] estados = request.getParameterValues("estado");
    	String[] complementos = request.getParameterValues("complemento");
    	String[] ceps = request.getParameterValues("cep");
    	String[] pais = request.getParameterValues("pais");
    	
    	for(int i=0; i < ruas.length; i++ ) {
    		Endereco endereco = new Endereco();
    		
    		endereco.setBairro(bairros[i]);
    		endereco.setCEP(Integer.parseInt(ceps[i]));
    		endereco.setCidade(cidades[i]);
    		endereco.setComplemento(complementos[i]);
    		endereco.setPais(pais[i]);
    		endereco.setRua(ruas[i]);
    		endereco.setEstado(estados[i]);
    		
    		lista.add(endereco);
    	}
    }
}
