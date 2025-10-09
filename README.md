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

## 🗄️ Configuração do Banco de Dados

O banco de dados deve ser previamente criado no MySQL com o seguinte comando:

```sql
CREATE DATABASE gestao_estoques_pedidos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

O arquivo de configuração do Hibernate (`hibernate.cfg.xml`) deve conter os parâmetros de conexão:

```xml
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/gestao_estoques_pedidos</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">sua_senha</property>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
    <property name="hibernate.hbm2ddl.auto">update</property>
    <property name="show_sql">true</property>
  </session-factory>
</hibernate-configuration>
```

---

## 🔐 Controle de Acesso

O sistema contempla dois tipos de usuários:  
- **Proprietário:** possui acesso completo a todas as funcionalidades, incluindo o histórico de vendas.  
- **Secretária:** acesso restrito, sem permissão para visualizar o histórico de vendas.  

A autenticação é realizada de forma simples, mediante validação de login e senha cadastrados no banco de dados.

---

## 🌐 Estrutura de Endpoints (API REST)

### 1️⃣ Login
| Método | Endpoint  | Descrição |
|---------|------------|-----------|
| POST | `/login` | Realiza a autenticação do usuário no sistema. |

**Exemplo de requisição:**
```json
{
  "usuario": "dono",
  "senha": "1234"
}
```

---

### 2️⃣ Clientes
| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| POST | `/clientes` | Cadastra um novo cliente. |
| GET | `/clientes` | Lista todos os clientes cadastrados. |
| GET | `/clientes/{id}` | Consulta informações de um cliente específico. |
| PUT | `/clientes/{id}` | Atualiza os dados de um cliente existente. |
| DELETE | `/clientes/{id}` | Remove um cliente do sistema. |

---

### 3️⃣ Fornecedores
| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| POST | `/fornecedores` | Adiciona um novo fornecedor. |
| GET | `/fornecedores` | Lista os fornecedores registrados. |
| GET | `/fornecedores/{id}` | Exibe informações de um fornecedor específico. |
| PUT | `/fornecedores/{id}` | Atualiza os dados de um fornecedor. |
| DELETE | `/fornecedores/{id}` | Remove um fornecedor do sistema. |

---

### 4️⃣ Orçamentos
| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| POST | `/orcamentos` | Cria um novo orçamento. |
| GET | `/orcamentos` | Lista todos os orçamentos. |
| GET | `/orcamentos/{id}` | Exibe informações detalhadas de um orçamento. |
| PUT | `/orcamentos/{id}` | Atualiza dados ou status de um orçamento. |
| DELETE | `/orcamentos/{id}` | Exclui um orçamento existente. |

---

### 5️⃣ Materiais e Kits
| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| POST | `/materiais` | Registra um novo material. |
| GET | `/materiais` | Lista os materiais cadastrados. |
| PUT | `/materiais/{id}` | Atualiza as informações de um material. |
| DELETE | `/materiais/{id}` | Remove um material do estoque. |
| POST | `/kits` | Cria um novo kit de materiais. |
| GET | `/kits` | Lista os kits disponíveis para uso. |

---

### 6️⃣ Histórico de Vendas
| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| GET | `/vendas` | Exibe o histórico geral de vendas. |
| GET | `/vendas/{ano}/{mes}` | Filtra as vendas realizadas em determinado período. |
| GET | `/vendas/detalhado/{idVenda}` | Gera relatório detalhado de uma venda específica (em PDF ou Word). |

---

## 📈 Possibilidades de Expansão

- Inclusão de autenticação por tokens (JWT).  
- Desenvolvimento de uma interface web integrada ao backend.  
- Implementação de relatórios estatísticos e gráficos.  
- Controle de permissões mais granular conforme o perfil do usuário. 

---
> Este documento descreve o funcionamento técnico e estrutural do sistema de **Gestão de Estoques e Pedidos**, apresentando suas principais funcionalidades, arquitetura e tecnologias utilizadas.
