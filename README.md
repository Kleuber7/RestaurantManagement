<div align="center">
<a href="https://www.fiap.com.br" target="_blank">
    <img src="https://on.fiap.com.br/theme/fiap/postech/pos-tech.png" height="100px" alt="FIAP" class="center"/>
</a>

[![Java11](https://img.shields.io/badge/devel-Java-brightgreen)](https://docs.oracle.com/en/java/javase/11)
[![SpringBoot](https://img.shields.io/badge/framework-SpringBoot-brightgreen)](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle)
</div>

# TRABALHO DE CONCLUSÃO - FASE 3 # - DEPLOY, ARQUITETURA E QUALIDADE DE SOFTWARE


### Documentação Swagger

[Link documentação Swagger](http://localhost:8080/swagger-ui/index.html)


Para executar o projeto , siga os passos abaixo:
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
## B) Documentação

### 1. Resumo

**1.1. História**

//TODO

**1.2. Justificativa**

//TODO

O Sistema de Gerenciamento de Devoluções oferece vários benefícios para a empresa, incluindo:

-   **Maior eficiência:** O sistema automatiza o processo de devolução de produtos, o que permite que as devoluções sejam processadas mais rapidamente e com mais precisão.
-   **Melhor experiência do cliente:** O sistema oferece aos clientes uma maneira rápida e fácil de solicitar a devolução de produtos. O sistema também fornece aos clientes informações sobre o status de suas devoluções, o que ajuda a reduzir a frustração.
-   **Menores custos:** O sistema automatizado ajuda a reduzir custos ao eliminar a necessidade de trabalho manual. O sistema também ajuda a reduzir custos ao prevenir fraudes e erros.

//Exemplo cima -> Remover após atualizar


- **1.3. Domínio e Subdomínio**

//TODO


**1.4. Eventos**

//TODO

### 2. Comandos

//TODO

//exemplos abaixo

-   **Comando "Solicitar Devolução":** Enviado pelo cliente através do sistema para iniciar o processo de devolução.
-   **Comando "Registrar Recebimento":** Registrado pelo funcionário do armazém após o recebimento do produto devolvido.
-   **Comando "Aprovar Devolução":** Enviado por um funcionário autorizado após a análise do motivo e condição do produto.
-   **Comando "Reembolsar Cliente":** Enviado para o sistema financeiro para processar o reembolso do cliente após a aprovação da devolução.

###  3. Agregados

-   **Pedido:** O agregado Pedido pode gerenciar o histórico e informações do pedido associado à devolução.
-   **Produto:** O agregado Produto pode gerenciar informações do produto devolvido, incluindo quantidade e detalhes do item.
-   **Devolução:** O agregado Devolução pode ser o principal responsável por gerenciar o processo de devolução, incluindo o motivo, status, itens devolvidos e histórico de eventos relacionados.

###  4. Fluxo do Processo

//TODO


###  5. Backlog

//TODO

Durante o desenvolvimento de um projeto, é comum encontrar tarefas que ainda não foram concluídas, seja por questões de prioridade ou por exigirem mais tempo e esforço. Para organizar e priorizar essas pendências, criamos o backlog. Abaixo estão algumas das tarefas planejadas para serem desenvolvidas no futuro:

-   Criação dos Map Struct para mapear objetos de DTO para Entity, e vice-versa.
-   Criação da camada de Serviço de toda implementação de regra de negócio, utilizando alguns Design Parterns como Strategy, entre outros.
-   Criação de Interface de Serviço.
-   Criação de Interface de Controller.
-   Definir e Viabilizar como será o recebimento do retorno da central de análise de produto.

## C) Participantes

-   RM353873 - Kleuber Costa
-   RM354111 - Felipe Oliveira
-   RM354482 - Letícia Oliveira
-   RM354525 - Marcello Caseiro
-   RM355621 - Paulo Bof
