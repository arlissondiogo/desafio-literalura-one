# 📚 Literalura

Projeto desenvolvido como desafio do programa **Oracle Next Education (ONE)**, com foco em **Java, Spring Boot, Spring Data JPA e PostgreSQL**.

O sistema consome dados da API pública **Gutendex**, armazena livros e autores em um banco de dados relacional e oferece consultas via menu interativo no terminal.

---

## 🚀 Funcionalidades

* 🔍 Buscar livro por título (API Gutendex)
* 💾 Salvar livros e autores no banco de dados
* 📖 Listar livros salvos
* ✍️ Listar autores registrados
* 🧓 Listar autores vivos em um determinado ano
* 🌍 Exibir quantidade de livros por idioma

> **Observação:** Para simplificação do desafio, cada livro é associado apenas ao **primeiro autor retornado pela API**.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* [**API Gutendex**](https://gutendex.com/)


---

## 🗂️ Estrutura do Projeto

```
src/main/java/com/example.literalura
 ├── controller
 │   └──BookApiClient
 ├── model
 │   ├── Author.java
 │   ├──AuthorApiDTO.java
 │   ├── Book.java
 │   └── GutendexResponse.java
 ├── repository
 │   ├── AutorRepository.java
 │   └── LivroRepository.java
 ├── service
 │   ├── IJsonConversor.java
 │   └── JsonConversor.java
 ├── util
 │   └── Menu.java
 └── LiteraluraApplication
```

---

## 🧠 Modelagem do Banco de Dados

### 📘 Livro

* id
* título
* idiomas
* número de downloads
* autor (ManyToOne)

### ✍️ Autor

* id
* nome
* ano de nascimento
* ano de falecimento

Cada livro possui **um autor**, e um autor pode estar relacionado a **vários livros**.

---

## 🖥️ Como Executar o Projeto

### Pré-requisitos

* Java 17+
* PostgreSQL
* Maven

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/arlissondiogo/desafio-literalura-one.git
```

2. Configure as variáveis de ambiente:

```bash
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

3. Configure o `application.properties`:

```properties
spring.application.name=literalura
spring.datasource.url=jdbc:postgresql://localhost:5433/livros_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

4. Execute o projeto:

```bash
mvn spring-boot:run
```

---

## 📊 Exemplo de Uso

```
===== LITERALURA =====
1 - Buscar livro por título
2 - Listar livros salvos
3 - Listar autores registrados
4 - Listar autores vivos em determinado ano
5 - Listar livros em determinado idioma
0 - Sair
```

---

## 📌 Aprendizados

* Consumo de APIs externas em Java
* Persistência de dados com Spring Data JPA
* Relacionamentos entre entidades (OneToMany / ManyToOne)
* Derived Queries
* Uso de Streams para estatísticas
* Boas práticas com variáveis de ambiente

---

## 📄 Licença

Este projeto é apenas para fins educacionais.
