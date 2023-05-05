package br.com.fiap.livraria.service.impl;

import br.com.fiap.livraria.controller.dto.AuthDTO;
import br.com.fiap.livraria.controller.dto.JwtDTO;
import br.com.fiap.livraria.controller.dto.UserCreateDTO;
import br.com.fiap.livraria.controller.dto.UserDTO;
import br.com.fiap.livraria.models.entity.User;
import br.com.fiap.livraria.repository.UserRepository;
import br.com.fiap.livraria.security.JwtTokenUtil;
import br.com.fiap.livraria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    private final JwtTokenUtil jwtTokenUtil;
@Autowired
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(JwtTokenUtil jwtTokenUtil,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository){
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserCreateDTO userCreateDTO){
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        User savedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setUsername(savedUser.getUsername());
        return userDTO;
    }

    public JwtDTO login(AuthDTO authDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(),
                            authDTO.getPassword())
            );
        }catch (AuthenticationException e){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    e.getMessage());
        }
        String token = jwtTokenUtil.generateToken(authDTO.getUsername());
        JwtDTO jwtDTO = new JwtDTO();
        return jwtDTO;
    }
}
