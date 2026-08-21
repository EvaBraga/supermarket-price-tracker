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
		
		validaId(id);
		validaNome(nome);
		
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
		if (precos.get(sp) == null) {
			throw new IllegalArgumentException("Preço Inexistente");
		}
		return precos.get(sp);
		
	}

	public String removerPreco(Supermercado sp) {
		if (precos.get(sp) == null) {
			throw new IllegalArgumentException("Preço inexistente");
		}
		precos.remove(sp);
		return "Preço removido com sucesso.";
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

	/**
	 * Valida o nome do Produto, lança uma excessão caso o nome seja nulo ou vazio
	 * @param nome o nome do Produto
	 */
	public static void validaNome(String nome) {
		if(nome == null) {
			throw new NullPointerException("Nome não pode ser nulo");
		}
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		}
	}
	
	/**
	 * Valida o ID do Produto, lança uma excessão caso o ID seja nulo ou vazio
	 * @param nome o ID do Produto
	 */
	public static void validaId(String id) {
		if(id == null) {
			throw new NullPointerException("ID não pode ser nulo");
		}
		if(id.trim().isEmpty()) {
			throw new IllegalArgumentException("ID inválido");
		}
		//CASO QUEIRA QUE O ID SEJA COMPOSTO SÓ POR NÚMEROS
//		for(int i = 0; i < id.length(); i++) {
//			if(!Character.isDigit(id.charAt(i))) {
//				throw new IllegalArgumentException("ID deve ser composto apenas de números");
//			}
//		}
	}
	

}
