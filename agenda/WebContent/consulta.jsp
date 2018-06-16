<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" java.util.List,
					java.text.SimpleDateFormat,agenda.model.ContatoDao,
					agenda.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/telaprincipal.css" rel="stylesheet">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Agenda Web</title>
<title>Insert title here</title>
</head>
<%
	List<Contato> listaDeContato = new ContatoDao().consultaPorQualquerTexto(request.getParameter("busca"));
%>
<%
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
%>
<%
	int contador = 1;
%>
<body>
	<div class="container">
		<hr>
		<%
			if (listaDeContato.size() > 0 && request.getParameter("busca") != ""
					&& !request.getParameter("busca").contains("%")) {
		%>
		<div class="row">
			<div class="table table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<td align=center>Contato</td>
							<td align=center>Ações</td>
						</tr>
					</thead>
					<tbody id="minhaTabela">
						<%
							for (Contato contato : listaDeContato) {
						%>
						<tr>

							<td align=center><button class="accordion">
									<%=contato.getNome()%>
									<%=contato.getSobrenome()%></button>
								<div class="panel">
									Nome Fonetico:
									<%=contato.getNomeFonetico()%></br> Sobrenome Fonetico:
									<%=contato.getSobrenomeFonetico()%><br> Empresa:
									<%=contato.getEmpresa()%><br> Empresa Fonetico:
									<%=contato.getEmpresaFonetico()%><br> Data de Nascimento:
									<%=contato.getDataAniversario()%><br>
									<%
										for (String url : contato.getListaURL()) {
									%>
									Url:
									<%=url%><br>
									<%
										}
									%>
									<%
										for (String email : contato.getListaEmail()) {
									%>
									Email:
									<%=email%><br>
									<%
										}
									%>
									<%
										for (String telefone : contato.getListaTelefone()) {
									%>
									Telefone:
									<%=telefone%><br>
									<%
										}
									%>
									<%
										for (String redesocial : contato.getListaRedeSocial()) {
									%>
									Rede Social:
									<%=redesocial%><br>
									<%
										}
									%>
									<%
										for (Endereco endereco : contato.getListaEndereco()) {
									%>
									Endereco:
									<%=endereco.getRua()%>
									<%=endereco.getComplemento()%>
									<%=endereco.getBairro()%>
									<%=endereco.getCidade()%>,
									<%=endereco.getCEP()%>
									-
									<%=endereco.getEstado()%>
									<%=endereco.getPais()%><br>
									<%
										}
									%>
								</div></td>
							<td align=center>
								<form action="Exclui" method="post">
									<input type="hidden" value="<%=contato.getId()%>" name="id" />
									<input type="image"
										src="img/sharp_delete_forever_black_18dp.png"
										value="<%=contato.getId()%>" name="id" />
								</form>

								<form action="editar-contato.jsp" method="post">
									<input type="hidden" value="<%=contato.getId()%>" name="id" />

									<input type="image" src="img/sharp_edit_black_18dp.png"
										value="<%=contato.getId()%>" name="id" />
								</form>
							</td>
						</tr>
						<%
							contador++;
						%>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<div class="col pull-center">
				<ul class="pagination" id="myPager"></ul>
			</div>
		</div>
		<br>
		<%
			} else {
		%>
		<h6><strong>Nada encontrado!</strong></h6>
		<%
			}
		%>
	</div>
	<script type="text/javascript" src="js/paginacao.js"></script>
	<script type="text/javascript" src="js/painel.js"></script>
</body>
</html>