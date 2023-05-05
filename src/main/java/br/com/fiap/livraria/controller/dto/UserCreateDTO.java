package br.com.fiap.livraria.controller.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
}
