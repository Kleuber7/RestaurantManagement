<div align="center">
<a href="https://www.fiap.com.br" target="_blank">
    <img src="https://on.fiap.com.br/theme/fiap/postech/pos-tech.png" height="100px" alt="FIAP" class="center"/>
</a>

[![Java11](https://img.shields.io/badge/devel-Java-brightgreen)](https://docs.oracle.com/en/java/javase/11)
[![SpringBoot](https://img.shields.io/badge/framework-SpringBoot-brightgreen)](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle)
</div>

# TRABALHO DE CONCLUSÃO - FASE 3 # - DEPLOY, ARQUITETURA E QUALIDADE DE SOFTWARE

## API de Gerenciamento de Restaurantes

----
Esta API permite a interação com um sistema de gerenciamento de restaurantes, fornecendo funcionalidades para criar,
atualizar, consultar informações relacionadas a restaurantes, reservas e avaliações. A API foi projetada para
atender tanto clientes quanto administradores.

### Funcionalidades Principais:

- **Cadastro de Restaurantes:**
    - O sistema permite cadastrar restaurantes, fornecendo informações como nome, localização, tipo de cozinha,
      horários de funcionamento e capacidade.
    - O sistema permite: buscar restaurantes por nome
    - O sistema permite: buscar restaurantes por cozinha
    - O sistema permite: buscar restaurantes por CEP
    - Não é permitido criar restaurante com capacidade menor que 20 mesas
- **Reserva de Mesas:**
    - O sistema permite criar reservas para datas e horários específicos.
    - Não é possível criar reserva para um número de pessoa menor que 1
    - Administradores podem gerenciar reservas e visualizar as informações de disponibilidade.
    - Não é possível a criação de reservas fora do horário de funcionamento dos restaurantes
    - Não é possível criar uma reserva se a quantidade de mesas requeridas seja maior que a capacidade do restaurante
- **Avaliações e Comentários:**
    - Os clientes podem adicionar avaliações dos restaurantes.
    - Avaliações incluem classificações de estrelas e comentários detalhados.
    - O sistema só permite avaliar o restaurante se o usuário fizer uma reserva e ter o status `FINESHED`, garantindo
      que ele frequentou o restaurante
- **Clientes:**
    - É possível criar um cliente contendo: Nome, email e telefone
    - É possível buscar um cliente pelo seu ID

### Documentação Swagger: [Link da documentação](http://localhost:8080/swagger-ui/index.html)

----

## Para executar o projeto , siga os passos abaixo:
1.  **Download do projeto no GitHub:**  Primeiro, faça o download do projeto a partir do repositório no GitHub. Você pode clonar o repositório usando o seguinte comando:

    ```shell
    $ git clone https://github.com/Kleuber7/RestaurantManagement.git
    ```
4.  **Pré-requisitos:**  Antes de executar o projeto, certifique-se de ter instalado os seguintes componentes:
    ```shell  
    -   Maven (versão 3.x)
    -   Java 17
    -   PostgreSQL (versão 16.x)
     ```
5.  **No diretório do projeto:**  Navegue até o diretório onde você clonou o projeto:
    ```shell
    cd RestaurantManagement
    ```
6.  **Compilando e empacotando com o Maven:**  Execute o seguinte comando para compilar o projeto e gerar o arquivo JAR:
    ```shell
    mvn clean package
    ```
8.  **Executando a aplicação:**  Agora, inicie a aplicação com o seguinte comando:
    ```shell
    java -jar target/RestaurantManagement-1.0-SNAPSHOT.jar
    ```

## Design Arquitetônico:
## Clean Code em Testes:
## Qualidade de Software:
## Testes Unitários:
## Testes Integrados e Inspeção de Código:
## Testes de Integração no Controller, CI e BDD:
## Testes Não Funcionais:
## Medir cobertura de teste:
## Deploy:

### C) Participantes
- RM353873 - Kleuber Costa
- RM354111 - Felipe Oliveira
- RM354482 - Letícia Oliveira
- RM354525 - Marcello Caseiro
- RM355621 - Paulo Bof


