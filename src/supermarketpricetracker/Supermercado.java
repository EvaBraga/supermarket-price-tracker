package supermarketpricetracker;

import java.util.*;

public class Supermercado {
	private String id;
	private String nome;
	private List<Produto> produtos;
	public Supermercado(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		this.produtos = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supermercado other = (Supermercado) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
