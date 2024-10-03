package com.fiap.restaurant_management.templateDto;

import com.fiap.restaurant_management.interfaces.dto.CustomerDto;

public class CustomerTemplateDto {

    public static CustomerDto customerTemplate() {
        return CustomerDto.builder()
                .customerCode(null)
                .name("Maria")
                .email("example@exmaple.com")
                .phone("11999999999")
                .build();
    }

    public static CustomerDto customerTemplatecucumber(String name, String email, String phone) {
        return CustomerDto.builder()
                .customerCode(null)
                .name(name)
                .email(email)
                .phone(phone)
                .build();
    }
}
