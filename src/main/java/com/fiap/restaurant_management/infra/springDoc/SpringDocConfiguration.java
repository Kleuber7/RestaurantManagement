package com.fiap.restaurant_management.infra.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    String desc = "# API de Gerenciamento de Restaurantes\n" +
            "\n" +
            "Esta API permite a interação com um sistema de gerenciamento de restaurantes, fornecendo funcionalidades para criar, atualizar, consultar e excluir informações relacionadas a restaurantes, reservas e avaliações. A API foi projetada para atender tanto clientes quanto administradores.\n" +
            "\n" +
            "## Funcionalidades Principais:\n" +
            "\n" +
            "- **Restaurantes**:\n" +
            "  - Criar, visualizar, atualizar e deletar informações de restaurantes.\n" +
            "  - Gerenciar dados como nome, localização, tipo de culinária, capacidade e horário de funcionamento.\n" +
            "\n" +
            "- **Reservas**:\n" +
            "  - Permite aos clientes criar reservas para restaurantes.\n" +
            "  - Administradores podem gerenciar reservas e visualizar as informações de disponibilidade.\n" +
            "\n" +
            "- **Avaliações**:\n" +
            "  - Os clientes podem adicionar e visualizar avaliações dos restaurantes.\n" +
            "  - Avaliações incluem classificações de estrelas e comentários detalhados.\n" +
            "\n" +
            "## Tecnologias:\n" +
            "\n" +
            "- **Respostas**: Todas as respostas da API seguem o padrão JSON.\n" +
            "- **Erros**: O tratamento de erros é padronizado, retornando mensagens claras e códigos HTTP apropriados.\n" +
            "\n" +
            "## Público Alvo:\n" +
            "\n" +
            "Esta API é voltada para desenvolvedores que desejam integrar funcionalidades de gerenciamento de restaurantes em suas aplicações, seja para uso interno, em aplicativos móveis ou sistemas de reservas de terceiros.\n";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                )
                .info(new Info()
                        .title("Restaurant Management")
                        .description(desc)
                        .contact(new Contact()
                                .name("Time backend")
                                .email("felipeadmy@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                        )
                );
    }
}
