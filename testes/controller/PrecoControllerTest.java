package controller;

import java.math.BigDecimal;

import controller.PrecoController;
import controller.ProdutoController;
import controller.SupermercadoController;
import model.Produto;
import model.Supermercado;
import model.Produto.Categoria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PrecoControllerTest {
	private ProdutoController pc;
	private SupermercadoController sc;
	private PrecoController pcl;
	private Produto produto;
	private Supermercado supermercado;

	@BeforeEach
	void setUp() {
		pc = new ProdutoController();
		sc = new SupermercadoController();
		pcl = new PrecoController(sc, pc);

		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		sc.cadastrarSupermercado("SP001", "Atacadão");

		produto = pc.buscarProduto("P001");
		supermercado = sc.buscarSupermercado("SP001");
	}

	@Test
	void registrarPrecoValido() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("10.00"));

		assertEquals(new BigDecimal("10.00"), produto.consultarPreco(supermercado));
	}

	@Test
	void registrarPrecoNegativo() {
		try {
			pcl.registrarPreco("P001", "SP001", new BigDecimal("-10.00"));
		} catch (IllegalArgumentException iae) {
			assertEquals("O preço deve ser maior que zero", iae.getMessage());
		}
	}

	@Test
	void registrarPrecoZero() {
		try {
			pcl.registrarPreco("P001", "SP001", new BigDecimal("0.00"));
		} catch (IllegalArgumentException iae) {
			assertEquals("O preço deve ser maior que zero", iae.getMessage());
		}
	}

	@Test
	void registrarPrecoDecimal() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("7.44"));

		assertEquals(new BigDecimal("7.44"), produto.consultarPreco(supermercado));
	}

	@Test
	void registrarPrecoDiferentesSupermercados() {
		sc.cadastrarSupermercado("SP002", "Carrefour");

		pcl.registrarPreco("P001", "SP001", new BigDecimal("10.00"));
		pcl.registrarPreco("P001", "SP002", new BigDecimal("8.00"));

		Supermercado supermercado2 = sc.buscarSupermercado("SP002");

		assertEquals(new BigDecimal("10.00"), produto.consultarPreco(supermercado));
		assertEquals(new BigDecimal("8.00"), produto.consultarPreco(supermercado2));
	}

	@Test
	void registrarPrecoNovamenteMesmoSupermercado() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("10.00"));
		pcl.registrarPreco("P001", "SP001", new BigDecimal("8.00"));

		assertEquals(new BigDecimal("8.00"), produto.consultarPreco(supermercado));
	}

	@Test
	void registrarPrecoProdutoInexistente() {
		try {
			pcl.registrarPreco("P999", "SP001", new BigDecimal("10.00"));
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void registrarPrecoSupermercadoInexistente() {
		try {
			pcl.registrarPreco("P001", "SP999", new BigDecimal("10.00"));
		} catch (IllegalArgumentException iae) {
			assertEquals("Supermercado não encontrado.", iae.getMessage());
		}
	}

	@Test
	void consultarPrecoExistente() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("10.00"));

		assertEquals(new BigDecimal("10.00"), pcl.consultarPreco("P001", "SP001"));
	}

	@Test
	void consultarPrecoInexistente() {
		try {
			pcl.consultarPreco("P001", "SP001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Preço Inexistente", iae.getMessage());
		}
	}

	@Test
	void consultarPrecoProdutoInexistente() {
		try {
			pcl.consultarPreco("P999", "SP001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void consultarPrecoSupermercadoInexistente() {
		try {
			pcl.consultarPreco("P001", "SP999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Supermercado não encontrado.", iae.getMessage());
		}
	}

	@Test
	void consultarDepoisDeRegistrar() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("12.77"));

		assertEquals(new BigDecimal("12.77"), pcl.consultarPreco("P001", "SP001"));
	}

	@Test
	void consultarDepoisDeRemover() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("12.77"));
		pcl.removerPreco("P001", "SP001");

		try {
			pcl.consultarPreco("P001", "SP001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Preço Inexistente", iae.getMessage());
		}
	}

	@Test
	void removerPrecoExistente() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("10.00"));
		pcl.removerPreco("P001", "SP001");

		try {
			produto.consultarPreco(supermercado);
		} catch (IllegalArgumentException iae) {
			assertEquals("Preço Inexistente", iae.getMessage());
		}
	}

	@Test
	void removerPrecoInexistente() {
		try {
			pcl.removerPreco("P001", "SP001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Preço Inexistente", iae.getMessage());
		}
	}

	@Test
	void removerPrecoProdutoInexistente() {
		try {
			pcl.removerPreco("P999", "SP001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void removerPrecoSupermercadoInexistente() {
		try {
			pcl.removerPreco("P001", "SP999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Supermercado não encontrado.", iae.getMessage());
		}
	}

	@Test
	void pegaMenorPrecoUmPreco() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("12.77"));

		assertEquals(new BigDecimal("12.77"), pcl.pegaMenorPreco("P001"));
	}

	@Test
	void pegaMenorPrecoVariosPrecos() {
		sc.cadastrarSupermercado("SP002", "Carrefour");
		sc.cadastrarSupermercado("SP003", "Assaí");

		pcl.registrarPreco("P001", "SP001", new BigDecimal("12.77"));
		pcl.registrarPreco("P001", "SP002", new BigDecimal("7.44"));
		pcl.registrarPreco("P001", "SP003", new BigDecimal("12.80"));

		assertEquals(new BigDecimal("7.44"), pcl.pegaMenorPreco("P001"));
	}

	@Test
	void pegaMenorPrecoProdutoSemPreco() {
		try {
			pcl.pegaMenorPreco("P001");
		} catch (IllegalArgumentException iae) {
			assertEquals("O sistema não possui preços cadastrados", iae.getMessage());
		}
	}

	@Test
	void pegaMenorPrecoProdutoInexistente() {
		try {
			pcl.pegaMenorPreco("P999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void pegaMaiorPrecoUmPreco() {
		pcl.registrarPreco("P001", "SP001", new BigDecimal("12.77"));

		assertEquals(new BigDecimal("12.77"), pcl.pegaMaiorPreco("P001"));
	}

	@Test
	void pegaMaiorPrecoVariosPrecos() {
		sc.cadastrarSupermercado("SP002", "Carrefour");
		sc.cadastrarSupermercado("SP003", "Assaí");

		pcl.registrarPreco("P001", "SP001", new BigDecimal("12.77"));
		pcl.registrarPreco("P001", "SP002", new BigDecimal("7.44"));
		pcl.registrarPreco("P001", "SP003", new BigDecimal("12.80"));

		assertEquals(new BigDecimal("12.80"), pcl.pegaMaiorPreco("P001"));
	}

	@Test
	void pegaMaiorPrecoProdutoSemPreco() {
		try {
			pcl.pegaMaiorPreco("P001");
		} catch (IllegalArgumentException iae) {
			assertEquals("O sistema não possui preços cadastrados", iae.getMessage());
		}
	}

	@Test
	void pegaMaiorPrecoProdutoInexistente() {
		try {
			pcl.pegaMaiorPreco("P999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}
}