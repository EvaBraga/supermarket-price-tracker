package controller;

import java.math.BigDecimal;

import model.Produto;
import model.Supermercado;

public class PrecoController {

	private SupermercadoController sc;
	private ProdutoController pc;

	public PrecoController(SupermercadoController sc, ProdutoController pc) {
		this.sc = sc;
		this.pc = pc;
	}

	public void registrarPreco(String idProduto, String idSupermercado, BigDecimal preco) {

		Produto p = pc.buscarProduto(idProduto);
		Supermercado s = sc.buscarSupermercado(idSupermercado);

		p.registrarPreco(s, preco);
	}

	public BigDecimal consultarPreco(String idProduto, String idSupermercado) {

		Produto p = pc.buscarProduto(idProduto);
		Supermercado s = sc.buscarSupermercado(idSupermercado);

		return p.consultarPreco(s);
	}

	public void removerPreco(String idProduto, String idSupermercado) {

		Produto p = pc.buscarProduto(idProduto);
		Supermercado s = sc.buscarSupermercado(idSupermercado);

		p.removerPreco(s);
	}

	public BigDecimal pegaMenorPreco(String idProduto) {

		Produto p = pc.buscarProduto(idProduto);

		return p.getMenorPrecoProduto();
	}

	public BigDecimal pegaMaiorPreco(String idProduto) {

		Produto p = pc.buscarProduto(idProduto);

		return p.getMaiorPrecoProduto();
	}
}