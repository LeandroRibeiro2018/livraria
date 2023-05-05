package br.com.fiap.livraria.configuration;

import br.com.fiap.livraria.security.JwtAuthenticationEntrypoint;
import br.com.fiap.livraria.security.JwtFilter;
import br.com.fiap.livraria.security.JwtUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin").roles("ADMIN")
//                .and()
//                .withUser("user").password("{noop}user").roles("USER");
//    }
//
//    protected  void configure(HttpSecurity http) throws Exception{
//        http
//                .httpBasic()
//                .and()
//                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST, "/livros").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin().disable();
//    }
private final JwtAuthenticationEntrypoint  jwtAuthenticationEntrypoint;
    private final JwtUserDetailService jwtUserDetailService;

    private final JwtFilter jwtFilter;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint,
                          JwtUserDetailService jwtUserDetailService,
                          JwtFilter jwtFilter,PasswordEncoder passwordEncoder){
        this.jwtAuthenticationEntrypoint = jwtAuthenticationEntrypoint;
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;

    }
@Bean
@Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailService)
                .passwordEncoder(passwordEncoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntrypoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin().disable();

   }

}
