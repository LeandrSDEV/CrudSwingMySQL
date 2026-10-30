# CRUD Swing + MySQL

**Projeto de estudo.** Aplicação desktop em **Java Swing** que faz o CRUD de pessoas (nome e e-mail) numa tabela **MySQL** com **JDBC puro**. Feito no NetBeans para praticar os fundamentos: interface gráfica, eventos, acesso a banco e organização em camadas, sem frameworks.

![Java](https://img.shields.io/badge/Java-24-ED8B00?logo=openjdk&logoColor=white) ![Swing](https://img.shields.io/badge/Swing-GUI-5382A1) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white) ![JDBC](https://img.shields.io/badge/JDBC-Connector%2FJ_9.5-4479A1) ![NetBeans](https://img.shields.io/badge/NetBeans-Ant-1B6AC6?logo=apachenetbeanside&logoColor=white)

## O que o projeto exercita

- **Camadas simples**: `model` (entidade), `dao` (acesso a dados), `util` (conexão) e `view` (tela).
- **Padrão DAO**: `PessoaDAO` concentra o SQL. A tela não conhece JDBC.
- **JDBC**: `DriverManager`, `PreparedStatement` com parâmetros (`?`) em INSERT/UPDATE/DELETE e `try-with-resources` para fechar conexão, statement e result set.
- **Swing**: tela montada com o GUI Builder do NetBeans (`GroupLayout`, arquivo `.form`) e `ActionListener` nos botões.

## Funcionalidades

| Botão | Ação | SQL |
| --- | --- | --- |
| **Salvar** | cadastra uma pessoa com o nome e o e-mail digitados | `INSERT INTO pessoa (nome, email) VALUES (?, ?)` |
| **Listar** | mostra todos os registros na área de texto (`id - nome - email`) | `SELECT * FROM pessoa` |
| **Atualizar** | altera nome e e-mail da pessoa com o ID informado | `UPDATE pessoa SET nome=?, email=? WHERE id=?` |
| **Deletar** | remove a pessoa com o ID informado | `DELETE FROM pessoa WHERE id=?` |

## Estrutura

```
src/
├── model/Pessoa.java              entidade (id, nome, email)
├── dao/PessoaDAO.java             salvar, listar, atualizar, deletar
├── util/Conexao.java              abre a conexão JDBC com o MySQL
└── view/FormularioPessoa.java     JFrame com campos, botões e área de listagem
    (+ FormularioPessoa.form)      layout do GUI Builder do NetBeans
nbproject/, build.xml              projeto NetBeans (Ant)
```

## Banco de dados

O código usa o banco `crud_java` em `localhost:3306` e a tabela `pessoa` (`id`, `nome`, `email`). O repositório não traz script SQL. Um exemplo compatível com as consultas do DAO:

```sql
CREATE DATABASE crud_java;
USE crud_java;

CREATE TABLE pessoa (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);
```

## Configuração

A conexão é lida de variáveis de ambiente em `src/util/Conexao.java`:

| Variável | Padrão |
| --- | --- |
| `DB_URL` | `jdbc:mysql://localhost:3306/crud_java?useSSL=false&serverTimezone=UTC` |
| `DB_USER` | `root` |
| `DB_PASSWORD` | vazio |

## Como rodar

Pré-requisitos: **JDK 24** (o projeto compila com `source/target 24`), **MySQL** e o driver **MySQL Connector/J 9.5.0** (`.jar`).

**No NetBeans**

1. Abra a pasta como projeto.
2. Em *Properties → Libraries*, aponte o `mysql-connector-j-9.5.0.jar` para o caminho na sua máquina. O `project.properties` traz um caminho absoluto local.
3. Crie o banco e a tabela (seção acima) e defina `DB_PASSWORD` (e `DB_URL`/`DB_USER`, se forem diferentes do padrão).
4. *Run Project*. A classe principal é `view.FormularioPessoa$Main`.

**Pela linha de comando**

```bash
javac -cp mysql-connector-j-9.5.0.jar -d out \
  src/model/Pessoa.java src/util/Conexao.java src/dao/PessoaDAO.java src/view/FormularioPessoa.java

# Linux/macOS (no Windows, troque ":" por ";" no classpath)
java -cp "out:mysql-connector-j-9.5.0.jar" 'view.FormularioPessoa$Main'
```

## Limitações conhecidas

Como é um projeto de prática, ficaram de fora:

- validação dos campos (o ID precisa ser numérico em Atualizar/Deletar);
- mensagens de erro na tela (as exceções vão para o console).

Também ficou para depois listar os registros numa `JTable`.

---

**Leandro de Jesus Santos** — [LinkedIn](https://www.linkedin.com/in/leandro-js-santos/) · [Portfólio](https://leandrsdev.github.io/portfolio-web/)
