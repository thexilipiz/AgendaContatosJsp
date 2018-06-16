<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,
				java.text.SimpleDateFormat,
				agenda.*,agenda.model.ContatoDao"%>
<!DOCTYPE html">
<html>
<head>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Editar Contato</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/validarformulario.js"></script>
</head>
<body>
<%List<Contato> listaDeContato = new ContatoDao().carregarContatos();%>
<%Contato contatoAlterar = null; %>
<%for(Contato contato : listaDeContato){
	if(contato.getId() == Integer.parseInt(request.getParameter("id")))
		contatoAlterar = contato;
} %>
<%SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY"); %>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Editar Contato</h1>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col">
				 <form action="EdicaoContato" method="post">
					  <div class="form-group">
					    <label for="nome">Nome:</label>
					    <input type="text" class="form-control" name="nome" id="nome" value="<%= contatoAlterar.getNome() %>">
					  </div>
					  <div class="form-group">
					    <label for="pwd">Nome Fonético:</label>
					    <input type="text" class="form-control" id="nomefonetico" name="nomefonetico" value="<%= contatoAlterar.getNomeFonetico() %>">
					  </div>
					  <div class="form-group">
					    <label for="sobrenome">Sobrenome:</label>
					    <input type="text" class="form-control" id="sobrenome" name="sobrenome" value="<%= contatoAlterar.getSobrenome()%>">
					  </div>
					  <div class="form-group">
					    <label for="sobrenomefonetico">Sobrenome Fonético:</label>
					    <input type="text" class="form-control" id="sobrenomefonetico" name="sobrenomefonetico" value="<%= contatoAlterar.getSobrenomeFonetico()%>">
					  </div>
					  <div class="form-group">
					    <label for="empresa">Empresa:</label>
					    <input type="text" class="form-control" id="empresa" name="empresa" value="<%=contatoAlterar.getEmpresa()%>">
					  </div>
					  <div class="form-group">
					    <label for="empresafonetico">Empresa Fonético:</label>
					    <input type="text" class="form-control" id="empresafonetico" name="empresafonetico" value="<%=contatoAlterar.getEmpresaFonetico()%>">
					  </div>
					  <div class="form-group" id="grupotelefone">
					    <label for="telefone">Telefones:</label>
					    <%for(String telefone : contatoAlterar.getListaTelefone()){%>
					    <input type="text" class="form-control" name="telefones" value="<%=telefone%>" required>
					    <% }%><br>
					    <div class="input-group-append" id="addfone">
				    		<button class="btn btn-dark" type="button" onclick="adicionarcampotelefone();">+</button> &nbsp;
				  		</div>
					  </div>
					  <div class="form-group" id="grupoemail">
					    <label for="email">Emails:</label>
					    <%for(String email: contatoAlterar.getListaEmail()){ %>
					    <input type="email" class="form-control" name="email" value="<%=email%>" required>
					    <%}%><br>
					    <div class="input-group-append" id="addemail">
				    		<button class="btn btn-dark" type="button" onclick="adicionarcampoemail();">+</button>&nbsp;
				  		</div>
					  </div>
					  <div class="form-group" id="grupourl">
					    <label for="url">URL's:</label>
					    <% for(String url : contatoAlterar.getListaURL()){ %>
					    <input type="text" class="form-control" name="url" value="<%=url %>" required>
					    <%}%><br>
					    <div class="input-group-append" id="addurl">
				    		<button class="btn btn-dark" type="button" onclick="adicionarcampourl();">+</button>&nbsp;
				  		</div>
					  </div>
					  <div class="form-group">
					    <label for="grupoendereco">Endereços:</label>
					  </div>
					  <%for(Endereco endereco: contatoAlterar.getListaEndereco()){ %>
					  <div class="form-group row" id="grupoendereco">
					    <div class="col">
					    	<input type="text" class="form-control" name="rua" value="<%=endereco.getRua()%>" placeholder="Rua..." required>
					    </div>
					    <div class="col">
					    	<input type="text" class="form-control" name="bairro" value="<%=endereco.getBairro()%>" placeholder="Bairro.." required>
					   	</div>
					   	<div class="col">
					    	<input type="text" class="form-control" name="cidade" value="<%=endereco.getCidade()%>" placeholder="Cidade.." required>
					   	</div>
					   	<div class="w-100"></div>
					   	<div class="col">
					    	<input type="text" class="form-control" name="estado" value="<%=endereco.getEstado()%>" placeholder="Estado.." required>
					   	</div>
					   	<div class="col">
					    	<input type="text" class="form-control" name="complemento" value="<%=endereco.getComplemento()%>" placeholder="Complemento.." required>
					   	</div>
					   	<div class="col">
					    	<input type="text" class="form-control" name="cep" value="<%=endereco.getCEP()%>" placeholder="CEP..(Apenas números)" pattern="[0-9]+$" required>
					   	</div>
					   	<div class="col">
					    	<input type="text" class="form-control" name="pais" value="<%=endereco.getPais()%>" placeholder="Pais" required>
					   	</div>
					  </div>
					  <%}%>
					  <div class="form-group" id="addendereco">
				    	 <button class="btn btn-dark" type="button" onclick="adicionarcampoendereco();">+</button>
					  </div>
					  <div class="form-group">
					    <label for="datanascimento">Data de Nascimento:</label>
					    <input type="text" class="form-control" id="datanascimento" name="datanascimento" pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" value="<%=contatoAlterar.getDataAniversario()%>">
					  </div>
					  <div class="form-group" id="gruporedesocial">
					    <label for="redesocial">Redes Sociais:</label>
					    <%for(String redesocial: contatoAlterar.getListaRedeSocial()){ %>
					    <input type="text" class="form-control" name="redesocial" value="<%=redesocial%>"required>
					    <%}%>
					    <div class="input-group-append" id="addredesocial">
				    		<button class="btn btn-dark" type="button" onclick="adicionarcamporedesocial();">+</button> &nbsp;
				  		</div>
					  </div>
					  <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
					  <br>
					  <button type="submit" class="btn btn-dark">Salvar</button>
				</form> 
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/adicionarcampo.js"></script>
	<script type="text/javascript" src="js/removercampos.js"></script>
</body>
</html>