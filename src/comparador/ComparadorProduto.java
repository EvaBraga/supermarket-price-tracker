package comparador;
import java.math.*;
import java.util.Comparator;

import model.Produto;

public class ComparadorProduto implements Comparator<Produto> {

	@Override
	public int compare(Produto o1, Produto o2) {
		BigDecimal menor1 = o1.getMenorPrecoProduto();
		BigDecimal menor2 = o2.getMenorPrecoProduto();
		return menor1.compareTo(menor2);
	}

}
