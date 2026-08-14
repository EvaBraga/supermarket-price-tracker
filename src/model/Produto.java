package model;

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

	public enum Categoria {
		FRUTAS, FRIOS, CARNES, LATICINIOS, GRAOS, BEBIDAS

	}

	public BigDecimal getMenorPrecoProduto() {
		if (precos == null || precos.isEmpty()) {
			throw new IllegalArgumentException("O sistema não possui preços cadastrados");
		}
		return Collections.min(precos.values());

	}

	public BigDecimal getMaiorPrecoProduto() {
		if (precos == null || precos.isEmpty()) {
			throw new IllegalArgumentException("O sistema não possui preços cadastrados");
		}
		return Collections.max(precos.values());
	}

	public void registrarPreco(Supermercado supermercado, BigDecimal preco) {
		if (preco.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O preço deve ser maior que zero");
		}
		precos.put(supermercado, preco);
	}

	public BigDecimal consultarPreco(Supermercado sp) {
		return precos.get(sp);
	}

	public void removerPreco(Supermercado sp) {
		precos.remove(sp);
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
