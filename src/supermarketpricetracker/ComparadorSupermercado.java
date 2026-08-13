package supermarketpricetracker;

import java.util.Comparator;

public class ComparadorSupermercado implements Comparator<Supermercado> {

	@Override
	public int compare(Supermercado o1, Supermercado o2) {
		return o1.getNome().compareTo(o2.getNome());
	}

}
