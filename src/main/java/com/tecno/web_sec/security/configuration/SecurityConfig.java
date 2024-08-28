package com.tecno.web_sec.security.configuration;

import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/resources/**", "/css/**", "/error","/public/**").permitAll() // Permitir acceso a las páginas de login y error
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form
                .loginPage("/login") // URL del formulario de login
                .defaultSuccessUrl("/home", true) // Redirigir a home.html después de un login exitoso
                .failureHandler(customAuthenticationFailureHandler()) // Manejo de errores de autenticación
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL para cerrar sesión
                .logoutSuccessUrl("/login?logout") // Redirige al login después de cerrar sesión
                .invalidateHttpSession(true) // Invalida la sesión HTTP
                .deleteCookies("JSESSIONID") // Elimina cookies específicas, si es necesario
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedHandler(customAccessDeniedHandler()) // Manejo personalizado para acceso denegado
            );

        return http.build();
    }

    // Manejo personalizado de errores de autenticación
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return (request, response, exception) -> {
            // Registrar el error en los logs
            logger.error("Error en inicio de sesión: {}", exception.getMessage());

            // Redirige a la página de login con un parámetro de error que contiene el mensaje de excepción
            String errorMessage = "Error en inicio de sesión"; // Mensaje predeterminado
            if (exception.getMessage() != null) {
                errorMessage = exception.getMessage();
            }

            // Codificar el mensaje de error para el manejo de acentos.
            String encodedError = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8.toString());
            response.sendRedirect("/login?error=" + encodedError);
        };
    }

    // Manejo personalizado para acceso denegado
    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/error"); // Redirige a la página de error personalizada
        };
    }
}
