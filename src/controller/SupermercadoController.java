package controller;
import java.util.*;

import comparador.ComparadorSupermercado;
import model.Supermercado;


public class SupermercadoController {
	private HashMap<String, Supermercado> supermercados;

	public SupermercadoController() {
		super();
		this.supermercados = new HashMap<>();
	}
	
	public boolean cadastrarSupermercado(String id, String nome) {
		if (supermercados.containsKey(id)) {
			return false;
		}
		supermercados.put(id, new Supermercado(id, nome));
		return true;
	}
	
	public Supermercado buscarSupermercado(String id) {
		if (!supermercados.containsKey(id)) {
			throw new IllegalArgumentException("Supermercado inexistente.");
		}
		Supermercado sp = supermercados.get(id);
		return sp;
	}
	
	public String[] listarSupermercados() {
		List<Supermercado> mercados = new ArrayList<>(supermercados.values());
		Collections.sort(mercados, new ComparadorSupermercado());
		String[] out = new String[mercados.size()];
		int i = 0;
		for (Supermercado sp : mercados) {
			out[i] = sp.toString();
			i++;
		}
		return out;
	}
	
	public void deletarSupermercado(String id) {
		supermercados.remove(id);
	}
	
	
}
