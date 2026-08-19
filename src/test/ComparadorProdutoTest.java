package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparador.ComparadorProduto;
import model.Produto;
import model.Produto.Categoria;
import model.Supermercado;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ComparadorProdutoTest {
	private ComparadorProduto comparador;
	private Supermercado supermercado;

	@BeforeEach
	void setUp() {
		comparador = new ComparadorProduto();
		supermercado = new Supermercado("SP001", "Atacadão");
	}

	@Test
	void produto1MaisBaratoQueProduto2() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("5.00"));
		p2.registrarPreco(supermercado, new BigDecimal("10.00"));

		assertTrue(comparador.compare(p1, p2) < 0);
	}

	@Test
	void produto1MaisCaroQueProduto2() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("10.00"));
		p2.registrarPreco(supermercado, new BigDecimal("5.00"));

		assertTrue(comparador.compare(p1, p2) > 0);
	}

	@Test
	void produtosComMesmoPreco() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("10.00"));
		p2.registrarPreco(supermercado, new BigDecimal("10.00"));

		assertEquals(0, comparador.compare(p1, p2));
	}

	@Test
	void ordenacaoDeDoisProdutos() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("10.00"));
		p2.registrarPreco(supermercado, new BigDecimal("5.00"));

		List<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);

		Collections.sort(produtos, comparador);

		assertEquals(p2, produtos.get(0));
		assertEquals(p1, produtos.get(1));
	}

	@Test
	void ordenacaoDeVariosProdutos() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);
		Produto p3 = new Produto("P003", "Macarrão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("12.00"));
		p2.registrarPreco(supermercado, new BigDecimal("5.00"));
		p3.registrarPreco(supermercado, new BigDecimal("8.00"));

		List<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);

		Collections.sort(produtos, comparador);

		assertEquals(p2, produtos.get(0));
		assertEquals(p3, produtos.get(1));
		assertEquals(p1, produtos.get(2));
	}

	@Test
	void listaJaOrdenada() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);
		Produto p3 = new Produto("P003", "Macarrão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("5.00"));
		p2.registrarPreco(supermercado, new BigDecimal("8.00"));
		p3.registrarPreco(supermercado, new BigDecimal("10.00"));

		List<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);

		Collections.sort(produtos, comparador);

		assertEquals(p1, produtos.get(0));
		assertEquals(p2, produtos.get(1));
		assertEquals(p3, produtos.get(2));
	}

	@Test
	void listaEmOrdemInversa() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);
		Produto p3 = new Produto("P003", "Macarrão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("5.00"));
		p2.registrarPreco(supermercado, new BigDecimal("8.00"));
		p3.registrarPreco(supermercado, new BigDecimal("10.00"));

		List<Produto> produtos = new ArrayList<>();
		produtos.add(p3);
		produtos.add(p2);
		produtos.add(p1);

		Collections.sort(produtos, comparador);

		assertEquals(p1, produtos.get(0));
		assertEquals(p2, produtos.get(1));
		assertEquals(p3, produtos.get(2));
	}

	@Test
	void listaComPrecosIguais() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);
		Produto p3 = new Produto("P003", "Macarrão", Categoria.GRAOS);

		p1.registrarPreco(supermercado, new BigDecimal("10.00"));
		p2.registrarPreco(supermercado, new BigDecimal("10.00"));
		p3.registrarPreco(supermercado, new BigDecimal("10.00"));

		assertEquals(0, comparador.compare(p1, p2));
		assertEquals(0, comparador.compare(p2, p3));
		assertEquals(0, comparador.compare(p1, p3));
	}

	@Test
	void produtoSemPreco() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);

		try {
			comparador.compare(p1, p1);
		} catch (IllegalArgumentException iae) {
			assertEquals("O sistema não possui preços cadastrados", iae.getMessage());
		}
	}

	@Test
	void umProdutoSemPrecoEOutroComPreco() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);

		p2.registrarPreco(supermercado, new BigDecimal("10.00"));

		try {
			comparador.compare(p1, p2);
		} catch (IllegalArgumentException iae) {
			assertEquals("O sistema não possui preços cadastrados", iae.getMessage());
		}
	}

	@Test
	void ambosProdutosSemPreco() {
		Produto p1 = new Produto("P001", "Arroz", Categoria.GRAOS);
		Produto p2 = new Produto("P002", "Feijão", Categoria.GRAOS);

		try {
			comparador.compare(p1, p2);
		} catch (IllegalArgumentException iae) {
			assertEquals("O sistema não possui preços cadastrados", iae.getMessage());
		}
	}
}