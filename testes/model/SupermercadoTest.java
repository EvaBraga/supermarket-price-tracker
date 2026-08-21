package model;
import java.math.*;
import model.Produto;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import model.Supermercado;

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
	
   @Test
    void criaSupermercadoIdNulo() {
    	try {
    		Supermercado s = new Supermercado(null, "NovaVida");
    	} catch (NullPointerException iae) {
    		assertEquals("ID não pode ser nulo",iae.getMessage());
    	}
    }
    @Test
    void criaSupermercadoIdVazio() {
    	try {
    		Supermercado s = new Supermercado("   ", "NovaVida");
    	} catch (IllegalArgumentException iae) {
    		assertEquals("ID inválido",iae.getMessage());
    	}
    }

    @Test
    void criaSupermercadoNomeNulo() {
    	try {
    		Supermercado s = new Supermercado("SP005", null);
    	} catch (NullPointerException iae) {
    		assertEquals("Nome não pode ser nulo",iae.getMessage());
    	}
    }
    @Test
    void criaSupermercadoNomeVazio() {
    	try {
    		Supermercado s = new Supermercado("SP005", "   ");
    	} catch (IllegalArgumentException iae) {
    		assertEquals("Nome inválido",iae.getMessage());
    	}
    }

}
