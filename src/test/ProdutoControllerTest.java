package test;

import model.Produto;
import java.math.*;
import model.Supermercado;
import controller.ProdutoController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import model.Produto.Categoria;

public class ProdutoControllerTest {
	private ProdutoController pc;
	private Produto p;

	@BeforeEach
	void setUp() {
		pc = new ProdutoController();
		p = new Produto("P001", "Suco", Categoria.BEBIDAS);
	}

	@Test
	void cadastraProdutoOK() {
		Boolean cadastro = pc.cadastrarProduto("P001", "Feijão", Categoria.GRAOS);
		assertTrue(cadastro);
	}

	@Test
	void cadastrarDoisProdutosDiferentes() {
		Boolean cadastro = pc.cadastrarProduto("P001", "Feijão", Categoria.GRAOS);
		assertTrue(cadastro);

		Boolean cadastro2 = pc.cadastrarProduto("P002", "Maçã", Categoria.FRUTAS);
		assertTrue(cadastro2);
	}

	@Test
	void cadastrarProdutoIdExistente() {
		pc.cadastrarProduto("P001", "Feijão", Categoria.GRAOS);
		Boolean cadastro2 = pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		assertFalse(cadastro2);
	}

	@Test
	void verificaSeProdutoCadastradoFoiArmazenado() {
		pc.cadastrarProduto("P001", "Feijão", Categoria.GRAOS);
		int tamanho = pc.getProdutos().size();
		assertEquals(1, tamanho);
	}

	@Test
	void buscarProduto() {
		pc.cadastrarProduto(p.getId(), p.getNome(), p.getCategoria());
		assertEquals(p, pc.buscarProduto(p.getId()));
	}

	@Test
	void buscarProdutoInexistente() {
		try {
			pc.buscarProduto("P999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void buscarUsandoIdValido() {
		pc.cadastrarProduto("P001", "Suco", Categoria.BEBIDAS);
		assertEquals("P001", pc.buscarProduto("P001").getId());
	}

	@Test
	void buscarUsandoIdInexistente() {
		try {
			pc.buscarProduto("P999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void buscarDepoisDeCadastrar() {
		pc.cadastrarProduto("P001", "Suco", Categoria.BEBIDAS);
		assertEquals("Suco", pc.buscarProduto("P001").getNome());
	}

	@Test
	void listarProdutosQuandoExistemProdutos() {
		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		pc.cadastrarProduto("P002", "Maçã", Categoria.FRUTAS);

		Supermercado sp = new Supermercado("SP001", "Atacadão");

		pc.buscarProduto("P001").registrarPreco(sp, new BigDecimal("10.00"));
		pc.buscarProduto("P002").registrarPreco(sp, new BigDecimal("5.00"));

		assertEquals(2, pc.listarProdutosMenorPreco().length);
	}

	@Test
	void listarQuandoNaoExistemProdutos() {
		assertEquals(0, pc.listarProdutosMenorPreco().length);
	}

	@Test
	void listarVariosProdutos() {
		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		pc.cadastrarProduto("P002", "Feijão", Categoria.GRAOS);
		pc.cadastrarProduto("P003", "Maçã", Categoria.FRUTAS);

		Supermercado sp = new Supermercado("SP001", "Atacadão");

		pc.buscarProduto("P001").registrarPreco(sp, new BigDecimal("10.00"));
		pc.buscarProduto("P002").registrarPreco(sp, new BigDecimal("7.00"));
		pc.buscarProduto("P003").registrarPreco(sp, new BigDecimal("5.00"));

		assertEquals(3, pc.listarProdutosMenorPreco().length);
	}

	@Test
	void verificaOrdenacaoPorMenorPreco() {
		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		pc.cadastrarProduto("P002", "Feijão", Categoria.GRAOS);

		Supermercado sp = new Supermercado("SP001", "Atacadão");

		pc.buscarProduto("P001").registrarPreco(sp, new BigDecimal("10.00"));
		pc.buscarProduto("P002").registrarPreco(sp, new BigDecimal("5.00"));

		String[] produtos = pc.listarProdutosMenorPreco();

		assertEquals(pc.buscarProduto("P002").toString(), produtos[0]);
		assertEquals(pc.buscarProduto("P001").toString(), produtos[1]);
	}

	@Test
	void removerProdutoExistente() {
		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		pc.removerProduto("P001");

		assertEquals(0, pc.getProdutos().size());
	}

	@Test
	void removerProdutoInexistente() {
		try {
			pc.removerProduto("P999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void produtoRemovidoNaoPodeSerEncontrado() {
		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		pc.removerProduto("P001");

		try {
			pc.buscarProduto("P001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Produto não encontrado.", iae.getMessage());
		}
	}

	@Test
	void cadastrarNovamenteIdDepoisDaRemocao() {
		pc.cadastrarProduto("P001", "Arroz", Categoria.GRAOS);
		pc.removerProduto("P001");

		Boolean cadastro = pc.cadastrarProduto("P001", "Feijão", Categoria.GRAOS);

		assertTrue(cadastro);
		assertEquals("Feijão", pc.buscarProduto("P001").getNome());
	}
}