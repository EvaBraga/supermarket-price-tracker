package model;
import java.math.*;
import model.Produto;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import model.Supermercado;
import model.Produto.Categoria;

public class SupermercadoTest {
	private Supermercado supermercado;
	
	@BeforeEach
	void setUp() {
		Supermercado supermercado = 
				new Supermercado(
				"SP001",
				"Atacadão");
		
	}
	
	@Test
	void criarSupermercadoValido() {
		Supermercado s1 = new Supermercado("SP002", "Rede Economico");
		assertEquals("Rede Economico", s1.getNome());
		assertEquals("SP002", s1.getId());
	}
	
}
