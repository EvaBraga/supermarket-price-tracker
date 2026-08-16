package execucao;
import java.math.*;
import model.*;
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
		PrecoController pcl = new PrecoController(sc, pc);

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
			case 1: {
				System.out.println("Cadastrar Produto");
				System.out.println("ID do Produto: ");
				String id = sca.nextLine();
				System.out.println("Nome do Produto: ");
				String nome = sca.nextLine();
				System.out.println("Categoria do Produto");
				Categoria categoria = Categoria.valueOf(sca.nextLine().toUpperCase());
				pc.cadastrarProduto(id, nome, categoria);
				System.out.println("Produto Cadastrado Com Sucesso!");

			}

			case 2: {
				System.out.println("Buscar produto");
				System.out.println("ID do Produto: ");
				String id = sca.nextLine();
				Produto produto = pc.buscarProduto(id);
				System.out.println(produto);

			}

			case 3: {
				System.out.println("Listar Produtos Por Menor Preço");
				System.out.println(pc.listarProdutosMenorPreco());

			}

			case 4: {
				System.out.println("Remover Produto");
				System.out.println("ID do Produto: ");
				String id = sca.nextLine();
				pc.removerProduto(id);
				System.out.println("Produto Removido Com Sucesso!");

			}
			
			case 5: {
				System.out.println("Cadastrar Supermercado");
				System.out.println("ID do Supermercado: ");
				String id = sca.nextLine();
				System.out.println("Nome do Supermercado: ");
				String nome = sca.nextLine();
				sc.cadastrarSupermercado(id, nome);
				System.out.println("Supermercado Cadastrado Com Sucesso!");
				
			}
			
			case 6: {
				System.out.println("Buscar Supermercado");
				System.out.println("ID do Supermercado: ");
				String id = sca.nextLine();
				System.out.println(sc.buscarSupermercado(id));
			}
			
			case 7: {
				System.out.println("Listar Supermercados");
				System.out.println(sc.listarSupermercados());

			}
			
			case 8: {
				System.out.println("Remover Supermercado");
				System.out.println("ID do Supermercado");
				String id = sca.nextLine();
				sc.deletarSupermercado(id);
				System.out.println("Supermercado Removido Com Sucesso!");
			}
			
			case 9: {
				System.out.println("Registrar Preço");
				System.out.println("ID do Produto: ");
				String idProduto = sca.nextLine();
				System.out.println("ID do Supermercado: ");
				String idSupermercado = sca.nextLine();
				System.out.println("Preço: ");
				BigDecimal preco = sca.nextBigDecimal();
				pcl.registrarPreco(idProduto, idSupermercado, preco);
				System.out.println("Preço Registrado Com Sucesso!");
			}
			
			case 10: {
				System.out.println("Consultar Preço");
				System.out.println("ID do Produto: ");
				String idProduto = sca.nextLine();
				System.out.println("ID do Supermercado: ");
				String idSupermercado = sca.nextLine();
				System.out.println(pcl.consultarPreco(idProduto, idSupermercado));
			}
			
			case 11: {
				System.out.println("Remover Preço");
				System.out.println("ID do Produto: ");
				String idProduto = sca.nextLine();
				System.out.println("ID do Supermercado: ");
				String idSupermercado = sca.nextLine();
				pcl.removerPreco(idProduto, idSupermercado);
				System.out.println("Preço Removido Com Sucesso!");
			}
			
			case 0: {
				System.out.println("Saindo");
			}
			
			default: {
				System.out.println("Opção Inválida");
			}
			
			}
			

		} while (opcao != 0);
		sca.close();

	}
}