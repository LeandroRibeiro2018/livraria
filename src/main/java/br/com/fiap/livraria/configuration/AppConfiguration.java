package br.com.fiap.livraria.configuration;


import br.com.fiap.livraria.validator.LivroValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public LivroValidator livroValidator(){
        return new LivroValidator();
    }


}
