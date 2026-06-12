# 💰 FinControl

Sistema de Controle Financeiro Pessoal desenvolvido em Java 8, utilizando Swing para interface gráfica e MySQL para persistência de dados.

---

## 📖 Sobre o Projeto

O FinControl foi desenvolvido com o objetivo de auxiliar usuários no gerenciamento de suas finanças pessoais, permitindo o controle de receitas, despesas, contas bancárias, categorias e relatórios financeiros.

O projeto foi construído aplicando conceitos de:

- Programação Orientada a Objetos (POO)
- MVC (Model-View-Controller)
- DAO (Data Access Object)
- JDBC
- Banco de Dados Relacional
- Herança e Polimorfismo
- Interfaces e Enumerações

---

## 🚀 Funcionalidades

### 👤 Usuários

- Cadastro de usuários
- Login no sistema
- Validação de credenciais

### 🏦 Contas

- Cadastro de contas
- Edição de contas
- Exclusão de contas
- Consulta de saldo
- Atualização automática do saldo

### 📂 Categorias

- Cadastro de categorias
- Edição de categorias
- Exclusão de categorias

### 💵 Receitas

- Cadastro de receitas
- Associação com conta
- Associação com categoria
- Atualização automática do saldo

### 💸 Despesas

- Cadastro de despesas
- Controle de status de pagamento
- Associação com conta
- Associação com categoria
- Atualização automática do saldo

### 📊 Relatórios

- Relatório por período
- Relatório por categoria
- Total de receitas
- Total de despesas
- Saldo final
- Gráfico de receitas x despesas

### 📜 Histórico de Transações

- Listagem completa de transações
- Filtro por período
- Filtro por categoria
- Filtro por tipo (Receita/Despesa)
- Exclusão de transações
- Atualização automática dos relatórios

### 📈 Dashboard

- Saldo total
- Receitas do mês
- Despesas do mês
- Atualização automática dos valores

---

## 🛠 Tecnologias Utilizadas

- Java 8
- Java Swing
- JDBC
- MySQL
- Eclipse IDE
- Git
- GitHub

---

## 📂 Estrutura do Projeto

```text
src
├── controller
├── dao
├── enums
├── interfaces
├── main
├── model
├── testes
├── util
└── view
```

---

## 🗄 Banco de Dados

### Banco

```sql
fincontrol
```

### Tabelas

```text
usuarios
contas
categorias
transacoes
```

### Relacionamentos

```text
Usuário
 ├── Contas
 ├── Categorias
 └── Transações

Conta
 └── Transações

Categoria
 └── Transações

Transação
 ├── Receita
 └── Despesa
```

---

## 📋 Modelo de Negócio

Cada usuário possui suas próprias:

- Contas
- Categorias
- Receitas
- Despesas

Todas as transações são vinculadas a:

```text
Usuário
↓
Conta
↓
Categoria
↓
Transação
```

O saldo das contas é atualizado automaticamente sempre que:

- Uma receita é cadastrada
- Uma despesa é cadastrada
- Uma transação é removida

---

## ▶ Como Executar

### 1. Clonar o Projeto

```bash
git clone https://github.com/Lukinh4s/FinControl.git
```

### 2. Importar no Eclipse

```text
File
→ Import
→ Existing Projects into Workspace
```

### 3. Criar Banco de Dados

```sql
CREATE DATABASE fincontrol;
```

### 4. Executar Script SQL

Importar o script SQL contendo as tabelas:

```text
database/fincontrol.sql
```

### 5. Configurar Conexão

Arquivo:

```text
src/dao/BDconexao.java
```

Exemplo:

```java
private final String host = "localhost";
private final String porta = "3306";
private final String banco = "fincontrol";
private final String usuario = "root";
private final String senha = "";
```

### 6. Executar o Sistema

Classe principal:

```text
src/main/Main.java
```

---

## 🧪 Testes

O projeto possui uma pasta destinada a testes:

```text
src/testes
```

Exemplos:

```text
TesteConexao
TesteUsuario
TesteConta
TesteCategoria
TesteReceita
TesteDespesa
TesteRelatorio
TesteSistemaCompleto
```

## 📚 Conceitos Aplicados

### Programação Orientada a Objetos

- Encapsulamento
- Herança
- Polimorfismo
- Abstração

### Arquitetura

- MVC
- DAO

### Banco de Dados

- CRUD
- Relacionamentos
- Chaves Primárias
- Chaves Estrangeiras
- JDBC

### Engenharia de Software

- Modelagem de Classes
- Organização em Camadas
- Separação de Responsabilidades

---

## 🎯 Objetivos do Projeto

- Aplicar conceitos de Engenharia de Software.
- Desenvolver um sistema desktop utilizando Java.
- Implementar persistência de dados utilizando MySQL.
- Utilizar boas práticas de programação orientada a objetos.
- Simular um sistema real de controle financeiro pessoal.

---

## 👨‍💻 Autor

Lucas Eduardo da Silva

Curso: Engenharia de Software  
Instituição: Uniasselvi

GitHub:
https://github.com/Lukinh4s

Projeto:
https://github.com/Lukinh4s/FinControl
---

## 📄 Licença

Projeto desenvolvido para fins acadêmicos e educacionais.