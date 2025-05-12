
# 🧩 Projeto ESIG - Desafio Técnico

Este projeto foi desenvolvido como parte do processo seletivo da **ESIG Group** para a vaga de Pessoa Desenvolvedora Java.

A proposta consiste em criar uma aplicação web com JavaServer Faces (JSF) que consolida os dados de salário das pessoas com base em dados cadastrados, realizando os cálculos corretamente e exibindo os resultados em uma interface amigável.

## 📌 Objetivo

Construir uma aplicação Web com:
- Uma **tela de listagem de pessoas** com seus respectivos salários consolidados.
- Um botão para **calcular/recalcular os salários**, conforme a lógica:
  - Valores com tipo `CREDITO` devem ser **somados** ao salário.
  - Valores com tipo `DEBITO` devem ser **subtraídos** do salário.

Além disso, alguns diferenciais foram implementados ou são sugeridos:
- Processamento assíncrono
- Exportação PDF com JasperReports
- CRUD de Pessoa
- Autenticação de usuários
- Testes unitários

## ⚙️ Tecnologias Utilizadas

- Java 8+
- JSF (JavaServer Faces)
- JPA / Hibernate
- PostgreSQL
- Apache Maven
- Apache Tomcat
- PrimeFaces (interface)


## 📋 Requisitos

Antes de executar o projeto, certifique-se de que os seguintes softwares estejam instalados em seu ambiente local:

* Java Development Kit (JDK) 8
* Apache Maven
* Apache Tomcat 8.5
* PostgreSQL
* IDE compatível com projetos Maven, como Eclipse ou IntelliJ IDEA([GitHub][2])


## 🚀 Instruções de Execução

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/Leandro208/projeto-esig.git
   ```



2. **Importe o projeto em sua IDE:**

   * Abra sua IDE preferida.
   * Importe o projeto como um projeto Maven existente.

3. **Configure o banco de dados:**

   * Crie um banco de dados PostgreSQL com o nome  `salarios`.
   * Atualize as configurações de conexão com o banco de dados no arquivo `persistence.xml`, localizado em `src/main/resources/META-INF/`.

     ```xml
     <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/salarios"/>
     <property name="javax.persistence.jdbc.user" value="seu_usuario"/>
     <property name="javax.persistence.jdbc.password" value="sua_senha"/>
     ```

4. **Compile o projeto:**

   No terminal, dentro do diretório do projeto, execute:

   ```bash
   mvn clean install
   ```
   Se estiver usando o eclipse, ele ja executa por padrão esses comandos ao importar como projeto Maven  


5. **Implante o projeto no servidor:**

   * Copie o arquivo WAR gerado em `target/projeto-esig.war` para o diretório `webapps` do Apache Tomcat.
   * Se estiver usando o eclipse, basta clicar em `Add and remove` e adicionar o projeto
   * Inicie o servidor Tomcat.

6. **Acesse a aplicação:**

   Abra o navegador e vá para:

     ```
   http://localhost:8080/projetoESIG
   ``` 



---

## 🛠️ Tecnologias Utilizadas

* Java 8
* JavaServer Faces (JSF)
* Hibernate
* PostgreSQL
* Apache Maven
* Apache Tomcat([GitHub][2], [GitHub][1])

---

## 📄 Observações

* Certifique-se de que o PostgreSQL esteja em execução e que as credenciais fornecidas no `persistence.xml` estejam corretas.

* Caso encontre problemas relacionados a dependências, execute `mvn clean install` novamente para garantir que todas as dependências sejam resolvidas corretamente.

---
