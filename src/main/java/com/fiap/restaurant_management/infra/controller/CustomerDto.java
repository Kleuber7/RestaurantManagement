package com.fiap.restaurant_management.infra.controller;

public record CustomerDto(
        String name,
        String email,
        String phone
) {
}
