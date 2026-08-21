package model;

import java.util.*;

public class Supermercado {
	private String id;
	private String nome;
	private List<Produto> produtos;
	
	public Supermercado(String id, String nome) {
		super();
		
		validaId(id);
		validaNome(nome);
		
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
	

	public String getId() {
		return id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	/**
	 * Valida o nome do Supermercado, lança uma excessão caso o nome seja nulo ou vazio
	 * @param nome o nome do Supermercado
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
	 * Valida o ID do Supermercado, lança uma excessão caso o ID seja nulo, vazio
	 * @param nome o ID do Supermercado
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
