package model;
import java.math.*;
import model.Produto;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import model.Supermercado;
import model.Produto.Categoria;


public class ProdutoTest {
	private Produto produto;
	private Supermercado supermercado;
	
	@BeforeEach
    void setUp() {
        produto = new Produto(
                "P001",
                "Arroz",
                Categoria.GRAOS
        );

        supermercado = new Supermercado(
                "S001",
                "Atacadão"
        );
    }
    @Test
    void criaProdutoIdValido() {
        assertEquals("P001", produto.getId());
    }
    @Test
    void criaProdutoIdNulo() {
    	try {
    		Produto p = new Produto(null, "Feijão", Categoria.GRAOS);
    	} catch (NullPointerException iae) {
    		assertEquals("ID não pode ser nulo",iae.getMessage());
    	}
    }
    @Test
    void criaProdutoIdVazio() {
    	try {
    		Produto p = new Produto("  ", "Feijão", Categoria.GRAOS);
    	} catch (IllegalArgumentException iae) {
    		assertEquals("ID inválido",iae.getMessage());
    	}
    }

    @Test
    void criaProdutoNomeValido() {
        assertEquals("Arroz", produto.getNome());
    }
    @Test
    void criaProdutoNomeNulo() {
    	try {
    		Produto p = new Produto("P002", null, Categoria.GRAOS);
    	} catch (NullPointerException iae) {
    		assertEquals("Nome não pode ser nulo",iae.getMessage());
    	}
    }
    @Test
    void criaProdutoNomeVazio() {
    	try {
    		Produto p = new Produto("P002", "   ", Categoria.GRAOS);
    	} catch (IllegalArgumentException iae) {
    		assertEquals("Nome inválido",iae.getMessage());
    	}
    }

    @Test
    void criaProdutoCategoriaValida() {
        assertEquals(Categoria.GRAOS, produto.getCategoria());
    }
    
    @Test
    void registraPrecoValido() {
    	BigDecimal preco = new BigDecimal("10.00");
    	produto.registrarPreco(supermercado, preco);
    	assertEquals(preco, produto.consultarPreco(supermercado));
    }
    
    @Test 
    void registraPrecoZerado() {
    	BigDecimal preco = new BigDecimal("0");
    	
    	try {
    		produto.registrarPreco(supermercado, preco);
    	} catch (IllegalArgumentException iae) {
    		assertEquals("O preço deve ser maior que zero",iae.getMessage());
    	}
    }
    
    @Test
    void registraPrecoNegativo() {
    	BigDecimal preco = new BigDecimal("-7");
    	try {
    		produto.registrarPreco(supermercado, preco);
    	} catch (IllegalArgumentException iae) {
    		assertEquals("O preço deve ser maior que zero", iae.getMessage());
    	}
    }
    
    @Test
    void registraMaisDeUmPrecoParaMercadosDiferentes() {
    	BigDecimal preco1 = new BigDecimal("8");
    	BigDecimal preco2 = new BigDecimal("12");
    	Supermercado sp = new Supermercado("SP003", "Tiririca");
    	produto.registrarPreco(supermercado, preco1);
    	produto.registrarPreco(sp, preco2);
    	
    	assertEquals(preco1, produto.consultarPreco(supermercado));
    	assertEquals(preco2, produto.consultarPreco(sp));
    	
    }
    
    @Test
    void registrarNovamentePrecoMesmoMercado() {
    	BigDecimal preco = new BigDecimal("4");
    	BigDecimal preco2 = new BigDecimal("4");
    	produto.registrarPreco(supermercado, preco);
    	produto.registrarPreco(supermercado, preco2);
    	assertEquals(preco2, produto.consultarPreco(supermercado));
    }
    
    @Test
    void consultarPrecoExistente() {
    	BigDecimal p = new BigDecimal("5");
    	produto.registrarPreco(supermercado, p);
    	assertEquals(p, produto.consultarPreco(supermercado));
    }
    
    @Test
    void consultarPrecoInexistente() {
    	try {
    		produto.consultarPreco(supermercado);
    	} catch (IllegalArgumentException iae) {
    		assertEquals("Preço Inexistente", iae.getMessage());
    	}
    }
    	
    @Test
    void removerPreco() {
    	BigDecimal p = new BigDecimal("5.50");
    	produto.registrarPreco(supermercado, p);
    	String saida = produto.removerPreco(supermercado);
    	assertEquals("Preço removido com sucesso.", saida);
    	
    }
    
    @Test
    void removerPrecoInexistente() {
    	try {
    		produto.removerPreco(supermercado);
    	} catch (IllegalArgumentException iae) {
    		assertEquals("Preço inexistente", iae.getMessage());
    	}
    }
    
    @Test
    void verificaMenorPreco_UnicoPreco() {
    	BigDecimal preco = new BigDecimal("12.77");
    	produto.registrarPreco(supermercado, preco);
    	assertEquals(preco, produto.getMenorPrecoProduto());
    }
    
    @Test
    void verificaMenorPrecoComVariosPrecos() {
    	BigDecimal p1 = new BigDecimal("12.77");
    	BigDecimal p2 = new BigDecimal("7.44");
    	BigDecimal p3 = new BigDecimal("12.80");
    	
    	Supermercado sp2 = new Supermercado("SP002", "Carrefour");
    	Supermercado sp3 = new Supermercado("SP003", "MixMatheus");
    	
    	produto.registrarPreco(supermercado, p1);
    	produto.registrarPreco(sp2, p2);
    	produto.registrarPreco(sp3, p3);
    	assertEquals(p2, produto.getMenorPrecoProduto());
    }
    
    @Test
    void verificaMaiorPreco_UnicoPreco() {
    	BigDecimal preco = new BigDecimal("12.77");
    	produto.registrarPreco(supermercado, preco);
    	assertEquals(preco, produto.getMaiorPrecoProduto());
    }
    
    @Test
    void verificaMaiorPrecoComVariosPrecos() {
    	BigDecimal p1 = new BigDecimal("12.77");
    	BigDecimal p2 = new BigDecimal("15.44");
    	BigDecimal p3 = new BigDecimal("9.32");
    	
    	Supermercado sp2 = new Supermercado("SP002", "Carrefour");
    	Supermercado sp3 = new Supermercado("SP003", "MixMatheus");
    	
    	produto.registrarPreco(supermercado, p1);
    	produto.registrarPreco(sp2, p2);
    	produto.registrarPreco(sp3, p3);
    	assertEquals(p2, produto.getMaiorPrecoProduto());
    }
    
    @Test
    void registraMenorPrecoValido() {
        BigDecimal preco = new BigDecimal("0.01");
    	produto.registrarPreco(
            supermercado,
            new BigDecimal("0.01")
        );
        assertEquals(preco, produto.consultarPreco(supermercado));
    }
    
    @Test
    void registrarPrecoGrande() {
        BigDecimal preco = new BigDecimal("9999.99");
    	produto.registrarPreco(
            supermercado,
            new BigDecimal("9999.99")
        );
        assertEquals(preco, produto.consultarPreco(supermercado)
        );
    }
    
    @Test
    void encontraMenorPrecoQuandoPrecosForemIguais() {
    	 BigDecimal preco = new BigDecimal("10.00");
    	 BigDecimal preco2 = new BigDecimal("10.00");
        Supermercado s2 = new Supermercado("S002", "Carrefour");
        produto.registrarPreco(supermercado, preco);
        produto.registrarPreco(s2, preco2);

        assertEquals(new BigDecimal("10.00"), produto.getMenorPrecoProduto());
    }
    
    @Test
    void deveEncontrarMenorPrecoEntreMuitosPrecos() {
        Supermercado s2 = new Supermercado("S002", "Carrefour");
        Supermercado s3 = new Supermercado("S003", "Extra");
        Supermercado s4 = new Supermercado("S004", "Assaí");
        Supermercado s5 = new Supermercado("S005", "Pão de Açúcar");
        produto.registrarPreco(supermercado, new BigDecimal("30.00"));
        produto.registrarPreco(s2, new BigDecimal("27.50"));
        produto.registrarPreco(s3, new BigDecimal("32.90"));
        produto.registrarPreco(s4, new BigDecimal("25.99"));
        produto.registrarPreco(s5,new BigDecimal("29.90"));
        assertEquals(new BigDecimal("25.99"), produto.getMenorPrecoProduto());
    }
    
    
    
}
