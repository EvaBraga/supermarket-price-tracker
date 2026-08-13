package supermarketpricetracker;
import java.util.*;
import java.math.BigDecimal;

public class Produto {
	private String id;
	private String nome;
	private HashMap<Supermercado, BigDecimal> precos;
	public Categoria categoria;

	
	
	public Produto(String id, String nome, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.precos = new HashMap<>();
		this.categoria = categoria;
	}
	
	public enum Categoria{
		FRUTAS,
		FRIOS,
		CARNES,
		LATICINIOS,
		GRAOS,
		BEBIDAS
		
	}
	
//	├── registrarPreco()
//	├── consultarPreco()
//	├── removerPreco()
//	
	
	public BigDecimal getMenorPrecoProduto() {
		return Collections.min(precos.values());
		
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(precos);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(precos, other.precos);
	}
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public HashMap<Supermercado, BigDecimal> getPrecos() {
		return precos;
	}
	public Categoria getCategoria() {
		return categoria;
	}

	
}
