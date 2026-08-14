package controller;
import java.util.*;

import comparador.ComparadorProduto;
import model.Produto;
import model.Produto.Categoria;

public class ProdutoController {
	private HashMap<String, Produto> produtos;

	public ProdutoController() {
		super();
		this.produtos = new HashMap<>();
	}
	
	public boolean cadastrarProduto(String id, String nome, Categoria categoria) {
		if (produtos.containsKey(id)) {
			return false;
		}
		produtos.put(id, new Produto(id, nome, categoria));
		return true;
	}
	
	public Produto buscarProduto(String id) {
		if (!produtos.containsKey(id)) {
			throw new IllegalArgumentException("Produto não encontrado.");
		}
		return produtos.get(id);
	}
	
	public String[] listarProdutosMenorPreco() {
		List<Produto> prod = new ArrayList<>(produtos.values());
		Collections.sort(prod, new ComparadorProduto());
		String[] out = new String[prod.size()];
		int i = 0;
		for (Produto p : prod) {
			out[i] = p.toString();
			i++;
		}
		return out;
	}
	
	public void removerProduto(String id) {
		produtos.remove(id);
	}
	
	
}
