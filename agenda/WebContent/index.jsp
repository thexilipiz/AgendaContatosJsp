<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import=" java.util.List,
					java.text.SimpleDateFormat,agenda.model.ContatoDao,agenda.Contato,
					agenda.Endereco"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/telaprincipal.css" rel="stylesheet">
<link href="css/botaoBusca.css" rel="stylesheet">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/adicionarcampo.js"></script>
<script type="text/javascript" src="js/remover-parametro.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Agenda</title>

<script type="text/javascript" language="javascript">
	function ClearForm() {
		window.location.href = "http://localhost:8080/agenda";
	}
</script>
</head>
<%
	List<Contato> listaDeContato = new ContatoDao().carregarContatos();
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
	int contador = 1;
%>

<body>
	<div class="container">
		<div class="row">
			<div class="col"">
				<h1>
					<img width="100" height="50" src="img/agenda-2.jpg" id="btnBusca"
						alt="Buscar" />
				</h1>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col">
				<div class="input-group mb-3">
					<form action="consulta.jsp">
						<div id="divBusca">
							<input type="text" id="txtBusca" placeholder="Buscar..."
								name="busca" /> <img src="img/sharp_search_black_18dp.png"
								id="busca" name="busca" alt="Buscar" />
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col text-center">
					<a href="novo-contato.jsp"><input type="image"
						src="img/baseline_add_black_18dp.png" class="botao"></a>
				</div>
			</div>
		</div>

		<%
			if (listaDeContato.size() > 0) {
		%>
		<div class="row">
			<div class="table table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<td align="center">Contato</td>
							<td align=center>Acões</td>
						</tr>
					</thead>
					<tbody id="minhaTabela">
						<%
							for (Contato contato : listaDeContato) {
						%>
						<tr>
							<td align=center><button class="accordion">
									<%=contato.getId()%>
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
									Endereço:
									<%=endereco.getRua()%>
									<%=endereco.getComplemento()%>
									<%=endereco.getBairro()%>
									<%=endereco.getCidade()%>,
									<%=endereco.getCEP()%>
									-
									<%=endereco.getEstado()%>
									<%=endereco.getPais()%>
									<%
										}
									%>
									<%-- <%for(Endereco endereco : contato.getListaEndereco()){ %>
									  Endereco: <%= endereco.getRua() %> <%= endereco.getComplemento() %> <%= endereco.getBairro() %> <%=endereco.getCidade() %>, 
									  <%=endereco.getCEP()%> - <%=endereco.getEstado() %> <%=endereco.getPais() %><br>
									  <% } %> --%>
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
			}
		%>

	</div>
	<script type="text/javascript" src="js/paginacao.js"></script>
	<script type="text/javascript" src="js/painel.js"></script>
</body>
</html>