package agenda;

import java.util.ArrayList;

public class Contato {
	private int id;
	private String nome;
	private String nomeFonetico;
	private String sobrenome;
	private String sobrenomeFonetico;
	private String empresa;
	private String empresaFonetico;
	private ArrayList<String> listaTelefone = new ArrayList<>();
	private ArrayList<String> listaEmail = new ArrayList<>();
	private ArrayList<String> listaURL = new ArrayList<>();
	private ArrayList<Endereco> listaEndereco = new ArrayList<>();
	private String dataAniversario;
	private ArrayList<String> listaRedeSocial = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFonetico() {
		return nomeFonetico;
	}

	public void setNomeFonetico(String nomeFonetico) {
		this.nomeFonetico = nomeFonetico;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSobrenomeFonetico() {
		return sobrenomeFonetico;
	}

	public void setSobrenomeFonetico(String sobrenomeFonetico) {
		this.sobrenomeFonetico = sobrenomeFonetico;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresaFonetico() {
		return empresaFonetico;
	}

	public void setEmpresaFonetico(String empresaFonetico) {
		this.empresaFonetico = empresaFonetico;
	}

	public ArrayList<String> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(ArrayList<String> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public ArrayList<String> getListaEmail() {
		return listaEmail;
	}

	public void setListaEmail(ArrayList<String> listaEmail) {
		this.listaEmail = listaEmail;
	}

	public ArrayList<String> getListaURL() {
		return listaURL;
	}

	public void setListaURL(ArrayList<String> listaURL) {
		this.listaURL = listaURL;
	}

	public ArrayList<Endereco> getListaEndereco() {
		return listaEndereco;
	}

	public void setListaEndereco(ArrayList<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}

	public String getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(String dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public ArrayList<String> getListaRedeSocial() {
		return listaRedeSocial;
	}

	public void setListaRedeSocial(ArrayList<String> listaRedeSocial) {
		this.listaRedeSocial = listaRedeSocial;
	}
	// public Contato() {
	// idBanco=0;
	// nome = "";
	// nomeFonetico = "";
	// sobrenome = "";
	// sobrenomeFonetico = "";
	// empresa = "";
	// empresaFonetico = "";
	// listaTelefone =
	// listaEmail = new ArrayList<>();
	// listaURL = new ArrayList<>();
	// listaEndereco = new ArrayList<>();
	// dataAniversario = Calendar.getInstance().getTime();
	// listaRedeSocial = new ArrayList<>();
	// }

}
