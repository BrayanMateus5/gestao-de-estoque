# gestao-de-estoque
Sistema de Gestão de Estoque e Pedidos 
# 💼 Sistema de Gestão de Estoques e Pedidos

O presente projeto consiste no desenvolvimento de um sistema backend em **Java puro**, utilizando **Hibernate** e **MySQL** como sistema gerenciador de banco de dados.  
O sistema foi concebido para atender às demandas de uma madeireira, possibilitando o gerenciamento eficiente de **clientes, fornecedores, materiais, orçamentos e histórico de vendas**, com perfis de acesso diferenciados para o proprietário e a secretária.

---

## 🎯 Objetivo Geral

Desenvolver um sistema de gestão que possibilite o controle informatizado de estoques e pedidos, permitindo o registro, consulta e atualização de informações de forma organizada e segura. O sistema tem como finalidade auxiliar na tomada de decisões e na otimização dos processos administrativos da empresa.

---

## 🧩 Objetivos Específicos

- Implementar funcionalidades para o cadastro e gerenciamento de clientes e fornecedores;  
- Registrar e acompanhar orçamentos e pedidos realizados;  
- Manter o controle do estoque de materiais e kits pré-definidos;  
- Armazenar e exibir o histórico de vendas de forma consolidada;  
- Garantir diferentes níveis de acesso conforme o tipo de usuário (proprietário ou secretária).

---

## ⚙️ Tecnologias Empregadas

- **Linguagem:** Java 17  
- **Banco de Dados:** MySQL  
- **Gerenciador de Dependências:** Maven  
- **Biblioteca para JSON:** Gson  
---

## 🧱 Arquitetura do Sistema

O sistema foi estruturado segundo o modelo em camadas, promovendo modularidade e separação de responsabilidades. As principais camadas são:

```
src/
├── model/           # Classes de entidade (mapeamento JPA)
├── dao/             # Acesso e manipulação de dados via Hibernate
├── service/         # Camada de regras de negócio e validações
├── controller/      # Camada responsável por gerenciar as requisições
├── util/            # Configurações e classes utilitárias
└── Main.java        # Classe principal do sistema
```

Essa arquitetura facilita a manutenção e a escalabilidade do sistema, permitindo futuras extensões sem comprometer o funcionamento das demais partes.

---

## 🔐 Controle de Acesso

O sistema contempla dois tipos de usuários:  
- **Proprietário:** possui acesso completo a todas as funcionalidades, incluindo o histórico de vendas.  
- **Secretária:** acesso restrito, sem permissão para visualizar o histórico de vendas.  

A autenticação é realizada de forma simples, mediante validação de login e senha cadastrados no banco de dados.
---

## 📈 Possibilidades de Expansão

- Inclusão de autenticação por tokens (JWT).  
- Desenvolvimento de uma interface web integrada ao backend.  
- Implementação de relatórios estatísticos e gráficos.  
- Controle de permissões mais granular conforme o perfil do usuário. 
