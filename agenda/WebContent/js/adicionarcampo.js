/**
 * 
 */
function adicionarcampotelefone(){
	var localDeAdicao = $("#grupotelefone");
	var botao = $("#addfone");
	
	botao.remove();
	
	localDeAdicao.append('<input type="text" class="form-control" name="telefones" required><br>');
	localDeAdicao.append('<div class="input-group-append" id="addfone">'+
	    	'<button class="btn btn-dark" type="button" onclick="adicionarcampotelefone();">+</button> &nbsp;');
}

function adicionarcampoemail(){
	var localDeAdicao = $("#grupoemail");
	var botao = $("#addemail");
	
	botao.remove();
	
	localDeAdicao.append('<input type="text" class="form-control" name="email" required><br>');
	localDeAdicao.append('<div class="input-group-append" id="addemail">'+
				    	'<button class="btn btn-dark" type="button" onclick="adicionarcampoemail();">+</button>&nbsp;'+'</div>');
}

function adicionarcampourl(){
	var localDeAdicao = $("#grupourl");
	var botao = $("#addurl");
	
	botao.remove();
	
	localDeAdicao.append('<input type="text" class="form-control" name="url" required>');
	localDeAdicao.append('<div class="input-group-append" id="addurl">'+
				    	'<button class="btn btn-dark" type="button" onclick="adicionarcampourl();">+</button>&nbsp;'+'</div>');
}

function adicionarcampoendereco(){
	var localDeAdicao = $("#grupoendereco");
	
	localDeAdicao.append('<div class="w-100"></div>');
	localDeAdicao.append('<div class="col">'+
						'<input type="text" class="form-control" name="rua" placeholder="Rua..." required></div>');
	localDeAdicao.append('<div class="col">'+
			'<input type="text" class="form-control" name="bairro" placeholder="Bairro.." required></div>');
	localDeAdicao.append('<div class="col">'+
			'<input type="text" class="form-control" name="cidade" placeholder="Cidade.." required></div>');
	localDeAdicao.append('<div class="w-100"></div>');
	localDeAdicao.append('<div class="col">'+
			'<input type="text" class="form-control" name="estado" placeholder="Estado.." required></div>');
	localDeAdicao.append('<div class="col">'+
			'<input type="text" class="form-control" name="complemento" placeholder="Complemento.." required></div>');
	localDeAdicao.append('<div class="col">'+
			'<input type="text" class="form-control" name="cep" placeholder="CEP..(Apenas números)" pattern="[0-9]+$" required></div>');
	localDeAdicao.append('<div class="col">'+
		'<input type="text" class="form-control" name="pais" placeholder="Pais.." required></div>');
}   

function adicionarcamporedesocial(){
	var localDeAdicao = $("#gruporedesocial");
	var botao = $("#addredesocial");
	
	botao.remove();
	
	localDeAdicao.append('<input type="text" class="form-control" name="redesocial" required><br>');
	localDeAdicao.append('<div class="input-group-append" id="addredesocial">'+
				    	'<button class="btn btn-dark type="button" onclick="adicionarcamporedesocial();">+</button>&nbsp;');
}