package execucao;
import java.util.*;
import controller.ProdutoController;
import controller.SupermercadoController;
import controller.PrecoController;
import model.Produto;
import model.Supermercado;


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	}
	
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
		
		switch (opcao) {
		case 1 : 
			System.out.println("ID do Produto: ");
			String id = sc.nextLine();
			
		}
		
	} while (opcao!= 0);
	
}
