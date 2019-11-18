package br.com.livraria.livraria.conf.email;

import br.com.livraria.livraria.model.CarrinhoLivros;
import br.com.livraria.livraria.model.dto.LivroDTO;

public class PaginaDeCompra {
	
	public String email(CarrinhoLivros carrinho) {
		return "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<meta charset=\"UTF-8\">\r\n" + 
				"		<link href=\"https://fonts.googleapis.com/css?family=Montserrat&display=swap\" rel=\"stylesheet\"> \r\n" + 
				"		<style>\r\n" + 
				"			body{\r\n" + 
				"				font-family: 'Montserrat', sans-serif;\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"			.capa{\r\n" + 
				"				width: 144px;\r\n" + 
				"				float: left;\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"			h2{\r\n" + 
				"				text-align: center;\r\n" + 
				"				font-weight: 800;\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"			span{\r\n" + 
				"				line-height: 45px;\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"			table, th, td {\r\n" + 
				"				border: 1px solid black;\r\n" + 
				"				text-align: center;\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"			footer{\r\n" + 
				"				font-size: 14px;\r\n" + 
				"				color: #878787;\r\n" + 
				"				position:absolute;\r\n" + 
				"				bottom:0;\r\n" + 
				"				width:99%;\r\n" + 
				"				text-align: center;\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<header>\r\n" + 
				"			<img src=\"https://i.imgur.com/hEUywDB.jpg\" class=\"capa\">\r\n" + 
				"			<h2>Compra feita com sucesso!</h2>\r\n" + 
				"		</header>\r\n" + 
				"		<section>\r\n" + 
				"			<br>\r\n" + 
				"			<span>Parabéns por ter feito em nossa livraria, esperamos que você \r\n" + 
				"				desfrute de um bom material de estudo.</span>\r\n" + 
				"			<br>\r\n" + 
				"			<span>Dados da compra:</span>\r\n" + 
							 tabela(carrinho) +
				"		</section>\r\n" + 
				"		<footer class=\"footer\">\r\n" + 
				"			<div class=\"footer-copyright text-center py-3\">© 2019 Copyright:\r\n" + 
				"				Livraria Assis\r\n" + 
				"			</div>\r\n" + 
				"		</footer>\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}
	
	private String tabela(CarrinhoLivros carrinho) {
		return "<table>\r\n" + 
				"				<thead>\r\n" + 
				"					<tr>\r\n" + 
				"						<th>Título</th>\r\n" + 
				"						<th>Descrição</th>\r\n" + 
				"						<th>Preço</th>\r\n" + 
				"					</tr>\r\n" + 
				"				</thead>\r\n" + 
				"				<tbody>\r\n" + 
				"					<tr>\r\n" + 
				
				itensSeparados(carrinho) + 
				
				"					</tr>\r\n" + 
				"				</tbody>\r\n" + 
				"				<tfoot>\r\n" + 
				"					<tr>\r\n" + 
				"						<th colspan=\"2\">TOTAL</th>\r\n" + 
				"						<th>" + carrinho.getTotal() + "</th>\r\n" + 
				"					</tr>\r\n" + 
				"				</tfoot>\r\n" + 
				"			</table>"
				;
	}
	
	private String itensSeparados(CarrinhoLivros carrinho) {
		String itens = "";
		
		for(LivroDTO livro : carrinho.getItens()) {
			itens += "<td>" + livro.getTitulo() + "</td>";
			itens += "<td>" + livro.getDescricao() + "</td>";
			itens += "<td>" + livro.getPreco() + "</td>";
		}
		return itens;
	}
}
