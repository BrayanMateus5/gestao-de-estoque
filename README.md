Gestão de Estoque e Pedidos (GEP)

Sistema em Java para controlar produtos, fornecedores, entradas e saídas de estoque, e registrar vendas. O sistema deve monitorar níveis mínimos de estoque para disparar alertas de reposição, além de registrar movimentações e permitir o cálculo de margem de lucro
--------------------------------
2. Principais Requisitos: 

Dados do Produto: Código, Nome, Descrição, Preço de Custo, Preço de Venda, Quantidade em Estoque, Nível de Estoque Mínimo.

Requisitos Funcionais (RF)

RF01: O sistema deve permitir o cadastro, alteração e exclusão de produtos.

RF02: O sistema deve permitir o cadastro, alteração e exclusão de Fornecedores.

RF03: O sistema deve permitir o cadastro, alteração e exclusão de Clientes.

RF04: O sistema deve permitir o registro de Entrada de Estoque (Compra), atualizando a quantidade do produto e registrando a movimentação.

RF05: O sistema deve permitir o registro de uma Venda para um Cliente.

RF06: Ao realizar uma Venda, o sistema deve permitir adicionar múltiplos Itens de Venda (Produtos e suas quantidades).

RF07: Ao finalizar uma Venda, o sistema deve dar baixa na quantidade de cada produto no estoque.

RF08: O sistema deve validar se há estoque suficiente antes de permitir uma Venda (não permitir estoque negativo).

RF09: O sistema deve disparar alertas de reposição quando a quantidade em estoque de um produto atingir seu nível mínimo.

RF10: O sistema deve registrar o histórico de preços de um produto.

RF11: O sistema deve gerar um relatório de inventário (produtos em estoque).

RF12: O sistema deve gerar um relatório de vendas.

Requisitos Não-Funcionais (RNF)

RNF01: O sistema será desenvolvido em Java.

RNF02: A persistência de dados será feita com JPA.

RNF03: O código será organizado em pacotes (ex: entidade, dao, servico, visao).

RNF04: O projeto será versionado no GitHub, com commits frequentes e um README com instruções de execução.



3. Principais Classes de Domínio

Produto:
id (Chave Primária) || nome || descricao || precoCusto || precoVenda || quantidadeEstoque || estoqueMinimo 
@ManyToOne Fornecedor (Muitos produtos para um fornecedor)
-----
Fornecedor:
id (Chave Primária) || nome || cnpj || email
 @OneToMany(mappedBy="fornecedor") 
List<Produto> (Um fornecedor para muitos produtos)
-----
Cliente:
id (Chave Primária) || nome || cpf || email
@OneToMany(mappedBy="cliente")
List<Venda> (Um cliente para muitas vendas)
-----
Venda:
id (Chave Primária) || dataVenda || valorTotal
@ManyToOne Cliente (Muitas vendas para um cliente)
@OneToMany(mappedBy="venda", cascade=CascadeType.ALL) 
List<ItemVenda> (Uma venda para muitos itens)
-----
ItemVenda (Classe associativa):
id (Chave Primária)
@ManyToOne Venda || @ManyToOne Produto || quantidade || precoUnitario 
-----
MovimentacaoEstoque:
id (Chave Primária) || @ManyToOne Produto
-----
DataMovimentacao:
tipo (ENUM: ENTRADA, SAIDA)
quantidade
-----
4. Estruturas de Dados Previstas 

List / ArrayList : Será usada extensivamente pelo JPA para gerenciar os relacionamentos OneToMany (ex: List<ItemVenda> em Venda).
-
Queue / LinkedList (Fila): utilizarei uma Fila simples para gerenciar os alertas de reposição. Quando um produto atingir o estoque mínimo, ele é adicionado à fila de alertas para ser exibido ao usuário.
-
Map / HashMap: Pode ser usado na camada de serviço para otimizar a montagem do carrinho de compras (Venda), usando o ID do produto como chave para acesso rápido.
-
Árvore binária: Uma árvore binária para produtos na camada de visão para exibir a lista de produtos sempre ordenada alfabeticamente de forma eficiente.
