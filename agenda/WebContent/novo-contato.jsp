<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="agenda.*,
				java.text.SimpleDateFormat"%>
<!DOCTYPE html">
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar Contato</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/validarformulario.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h3>Adicionar um novo Contato</h3>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col">
				<form action="Adiciona">
					<div class="form-group">
						<label for="nome">Nome:</label> <input type="text"
							class="form-control" name="nome" id="nome">
					</div>
					<div class="form-group">
						<label for="pwd">Nome Fonético:</label> <input type="text"
							class="form-control" id="nomefonetico" name="nomefonetico">
					</div>
					<div class="form-group">
						<label for="sobrenome">Sobrenome:</label> <input type="text"
							class="form-control" id="sobrenome" name="sobrenome">
					</div>
					<div class="form-group">
						<label for="sobrenomefonetico">Sobrenome Fonético:</label> <input
							type="text" class="form-control" id="sobrenomefonetico"
							name="sobrenomefonetico">
					</div>
					<div class="form-group">
						<label for="empresa">Empresa:</label> <input type="text"
							class="form-control" id="empresa" name="empresa">
					</div>
					<div class="form-group">
						<label for="empresafonetico">Empresa Fonético:</label> <input
							type="text" class="form-control" id="empresafonetico"
							name="empresafonetico">
					</div>
					<div class="form-group" id="grupotelefone">
						<label for="telefone">Telefone:</label> <input type="text"
							class="form-control" name="telefones" required><br>
						<div class="input-group-append" id="addfone">
							<button class="btn btn-dark" type="button"
								onclick="adicionarcampotelefone();">+</button>
						</div>
					</div>
					<div class="form-group" id="grupoemail">
						<label for="email">Email:</label> <input type="email"
							class="form-control" name="email" required><br>
						<div class="input-group-append" id="addemail">
							<button class="btn btn-dark" type="button"
								onclick="adicionarcampoemail();">+</button>
						</div>
					</div>
					<div class="form-group" id="grupourl">
						<label for="url">URL:</label> <input type="text"
							class="form-control" name="url" required><br>
						<div class="input-group-append" id="addurl">
							<button class="btn btn-dark" type="button"
								onclick="adicionarcampourl();">+</button>
						</div>
					</div>
					<div class="form-group">
						<label for="grupoendereco">Endereços:</label>
					</div>
					<div class="form-group row" id="grupoendereco">
						<div class="col">
							<input type="text" class="form-control" name="rua"
								placeholder="Rua..." required>
						</div>
						<div class="col">
							<input type="text" class="form-control" name="bairro"
								placeholder="Bairro.." required>
						</div>
						<div class="col">
							<input type="text" class="form-control" name="cidade"
								placeholder="Cidade.." required>
						</div>
						<div class="w-100"></div>
						<div class="col">
							<input type="text" class="form-control" name="estado"
								placeholder="Estado.." required>
						</div>
						<div class="col">
							<input type="text" class="form-control" name="complemento"
								placeholder="Complemento.." required>
						</div>
						<div class="col">
							<input type="text" class="form-control" name="cep"
								placeholder="CEP..(Apenas números)" pattern="[0-9]+$" required>
						</div>
						<div class="col">
							<input type="text" class="form-control" name="pais"
								placeholder="Pais" required>
						</div>
					</div>
					<div class="form-group" id="addendereco">
						<button class="btn btn-dark" type="button"
							onclick="adicionarcampoendereco();">+</button>
					</div>
					<div class="form-group">
						<label for="datanascimento">Data de Nascimento:</label> <input
							type="text" class="form-control" id="datanascimento"
							name="datanascimento"
							pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d">
					</div>
					<div class="form-group" id="gruporedesocial">
						<label for="redesocial">Rede Social:</label> <input type="text"
							class="form-control" name="redesocial" required><br>
						<div class="input-group-append" id="addredesocial">
							<button class="btn btn-dark" type="button"
								onclick="adicionarcamporedesocial();">+</button>
						</div>
					</div>
					<br>
					<button type="submit" onclick="return validarformulario();"
						class="btn btn-dark">Salvar</button>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/adicionarcampo.js"></script>
</body>
</html>