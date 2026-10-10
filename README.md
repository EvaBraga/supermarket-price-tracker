# SuperMarket Price Tracker

## Objetivo

Registrar e comparar os preços de produtos essenciais entre supermercados, para descobrir onde cada item sai mais barato.

A meta de longo prazo é transformar isso em uma pesquisa de preços dos alimentos que fazem parte da dieta brasileira, usando os dados para analisar diferenças entre mercados, variação por categoria, evolução dos valores, inflação e poder de compra. A coleta e a comparação utilizam como base supermercados do munícipio de Campina Grande

> **Status:** em desenvolvimento. Hoje o projeto está na fase de **Java puro**, rodando em memória e pelo terminal. As próximas etapas de Banco de dados, API, frontend e coleta automática de preços ainda não foram feitas.

## Funcionalidades

- **Supermercados:** cadastrar, buscar, listar e remover
- **Produtos:** cadastrar, buscar, listar e remover, cada um com uma categoria (`FRUTAS`, `FRIOS`, `CARNES`, `LATICINIOS`, `GRAOS` ou `BEBIDAS`)
- **Preços:** registrar, consultar, atualizar e remover o preço de um produto em um supermercado
- **Comparação:** listar os preços de um produto entre os supermercados que têm preço cadastrado e identificar o menor
- **Validações:** tratamento de erros e exceções com mensagens claras
- **Menu de terminal** para usar tudo isso
- **Testes unitários** com JUnit 5 

## Como rodar

### Requisitos

- JDK 17 ou superior (confira com `java -version`)
- Git

### Passo a passo

**1. Clone o repositório**

```bash
git clone git@github.com:EvaBraga/supermarket-price-tracker.git
cd supermarket-price-tracker
```

**2. Compile**

```bash
mkdir bin
javac -d bin $(find src/model src/controller -name "*.java") src/Main.java
```

**3. Execute**

```bash
java -cp bin Main
```

No Windows, use o Git Bash para os comandos funcionarem do mesmo jeito.

**Pelo Eclipse:** importe o projeto (`File > Import > Git > Projects from Git`), clique com o botão direito em `Main.java` e escolha `Run As > Java Application`.

### Usando o menu

```
=== SUPERMARKET PRICE TRACKER ===
1 - Cadastrar supermercado
2 - Cadastrar produto
3 - Registrar preço
4 - Consultar preço
5 - Comparar preços
6 - Listar supermercados
7 - Listar produtos
8 - Remover produto
9 - Remover supermercado
0 - Sair
```

Para testar rápido: cadastre dois supermercados (`1`), cadastre um produto como "Feijão" na categoria `GRAOS` (`2`), registre o preço dele em cada mercado (`3`) e compare (`5`).

Os dados ficam só em memória, então tudo é perdido ao fechar o programa.

### Rodando os testes

No Eclipse, clique com o botão direito na pasta `test` e escolha `Run As > JUnit Test`.

## Estrutura do projeto

```
supermarket-price-tracker/
├── src/
│   ├── model/
│   │   ├── Produto.java
│   │   └── Supermercado.java
│   ├── controller/
│   │   ├── ProdutoController.java
│   │   └── SupermercadoController.java
│   ├── test/
│   │   ├── ProdutoControllerTest.java
│   │   └── SupermercadoTest.java
│   └── Main.java
├── README.md
└── .gitignore
```

## Organização dos principais diretórios

### `model/`

As entidades do domínio.

- **`Supermercado`:** tem `id` e `nome`.
- **`Produto`:** tem `id`, `nome`, `categoria` e um mapa com o preço em cada supermercado (`Map<Supermercado, BigDecimal>`). É nele que ficam os métodos `registrarPreco`, `consultarPreco` e `removerPreco`.
- **`Categoria`:** enum com as categorias dos produtos, definido dentro de `Produto`.

Os preços usam `BigDecimal` para evitar erros de arredondamento. Não existe uma classe `Preco` por enquanto: guardar só o preço atual num mapa é suficiente, e ela só faz sentido quando entrar o histórico (data, promoção, origem da coleta).

### `controller/`

Guardam os dados em memória (`HashMap`) e concentram as regras de negócio: cadastrar, buscar, listar e remover, além das validações e das exceções (`IllegalArgumentException`) com mensagens explicando o problema.

- **`SupermercadoController`:** gerencia os supermercados.
- **`ProdutoController`:** gerencia os produtos e suas operações de preço.

### `test/`

Testes unitários em JUnit 5. Eles verificam as regras de negócio (cadastro válido, ID duplicado, preço inválido, busca de item inexistente, atualização e comparação de preços), e não apenas getters e setters.

### `Main.java`

Ponto de entrada. Mostra o menu no terminal e chama os controllers.

## Autoria

Desenvolvido por [Eva Braga](https://github.com/EvaBraga) e [Mateus Erik](https://github.com/mateuserikNA).
