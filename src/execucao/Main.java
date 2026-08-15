package execucao;

import java.util.*;
import controller.ProdutoController;
import controller.SupermercadoController;
import controller.PrecoController;
import model.Produto.Categoria;

public class Main {
	public static void main(String[] args) {

		ProdutoController pc = new ProdutoController();
		SupermercadoController sc = new SupermercadoController();
		Scanner sca = new Scanner(System.in);
		PrecoController precoController = new PrecoController(sc, pc);

		int opcao;

		do {
			System.out.println("\n=== COMPARADOR DE PREÇOS ===");
			System.out.println("1 - Cadastrar Produto");
			System.out.println("2 - Buscar Produto");
			System.out.println("3 - Listar produtos por menor preço");
			System.out.println("4 - Remover Produto");
			System.out.println("\n5- Cadastrar Supermercado");
			System.out.println("6 - Buscar Supermercado");
			System.out.println("7 - Listar Supermercados");
			System.out.println("8 - Remover Supermercado");
			System.out.println("\n9 Registrar Preço ");
			System.out.println("10 - Consultar Preço");
			System.out.println("11 - Remover Preço");
			System.out.println("\n0 - Sair");

			opcao = sca.nextInt();
			sca.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Cadastrar Produto");
				System.out.println("ID do Produto: ");
				String id = sca.nextLine();
				System.out.println("Nome do Produto: ");
				String nome = sca.nextLine();
				System.out.println("Categoria do Produto");
				Categoria categoria = Categoria.valueOf(sca.nextLine().toUpperCase());
				pc.cadastrarProduto(id, nome, categoria);

			}

		} while (opcao != 0);
		sca.close();
	}
}
