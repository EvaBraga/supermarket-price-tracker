package controller;

import model.Supermercado;
import controller.SupermercadoController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SupermercadoControllerTest {
	private SupermercadoController sc;
	private Supermercado sp;

	@BeforeEach
	void setUp() {
		sc = new SupermercadoController();
		sp = new Supermercado("SP001", "Atacadão");
	}

	@Test
	void cadastraSupermercadoOK() {
		Boolean cadastro = sc.cadastrarSupermercado("SP001", "Atacadão");
		assertTrue(cadastro);
	}

	@Test
	void cadastrarDoisSupermercadosDiferentes() {
		Boolean cadastro = sc.cadastrarSupermercado("SP001", "Atacadão");
		assertTrue(cadastro);

		Boolean cadastro2 = sc.cadastrarSupermercado("SP002", "Carrefour");
		assertTrue(cadastro2);
	}

	@Test
	void cadastrarSupermercadoIdExistente() {
		sc.cadastrarSupermercado("SP001", "Atacadão");

		Boolean cadastro2 = sc.cadastrarSupermercado("SP001", "Carrefour");
		assertFalse(cadastro2);
	}

	@Test
	void verificaSeSupermercadoCadastradoFoiArmazenado() {
		sc.cadastrarSupermercado("SP001", "Atacadão");

		int tamanho = sc.getSupermercados().size();
		assertEquals(1, tamanho);
	}

	@Test
	void buscarSupermercado() {
		sc.cadastrarSupermercado(sp.getId(), sp.getNome());

		assertEquals(sp, sc.buscarSupermercado(sp.getId()));
	}

	@Test
	void buscarSupermercadoInexistente() {
		try {
			sc.buscarSupermercado("SP999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Supermercado não encontrado.", iae.getMessage());
		}
	}

	@Test
	void listarSupermercados() {
		sc.cadastrarSupermercado("SP001", "Atacadão");

		assertEquals(1, sc.listarSupermercados().length);
	}

	@Test
	void listarQuandoNaoExistemSupermercados() {
		assertEquals(0, sc.listarSupermercados().length);
	}

	@Test
	void listarVariosSupermercados() {
		sc.cadastrarSupermercado("SP001", "Atacadão");
		sc.cadastrarSupermercado("SP002", "Carrefour");
		sc.cadastrarSupermercado("SP003", "Assaí");

		assertEquals(3, sc.listarSupermercados().length);
	}

	@Test
	void removerSupermercadoExistente() {
		sc.cadastrarSupermercado("SP001", "Atacadão");

		sc.deletarSupermercado("SP001");

		assertEquals(0, sc.getSupermercados().size());
	}

	@Test
	void removerSupermercadoInexistente() {
		try {
			sc.deletarSupermercado("SP999");
		} catch (IllegalArgumentException iae) {
			assertEquals("Supermercado não encontrado.", iae.getMessage());
		}
	}

	@Test
	void supermercadoRemovidoNaoPodeSerEncontrado() {
		sc.cadastrarSupermercado("SP001", "Atacadão");
		sc.deletarSupermercado("SP001");

		try {
			sc.buscarSupermercado("SP001");
		} catch (IllegalArgumentException iae) {
			assertEquals("Supermercado não encontrado.", iae.getMessage());
		}
	}
}