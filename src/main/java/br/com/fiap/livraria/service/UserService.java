package br.com.fiap.livraria.service;

import br.com.fiap.livraria.controller.dto.AuthDTO;
import br.com.fiap.livraria.controller.dto.JwtDTO;
import br.com.fiap.livraria.controller.dto.UserCreateDTO;
import br.com.fiap.livraria.controller.dto.UserDTO;

public interface UserService {

    UserDTO create(UserCreateDTO userCreateDTO);
    JwtDTO login(AuthDTO authDTO);
}
