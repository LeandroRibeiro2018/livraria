package br.com.fiap.livraria.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntrypoint  implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authExeption) throws IOException{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token.invalido");
    }
}
