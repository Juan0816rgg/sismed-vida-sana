package com.vidasana.sismed.config;

import com.vidasana.sismed.entity.Usuario;
import com.vidasana.sismed.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner inicializarUsuario(UsuarioRepository usuarioRepository) {

        return args -> {

            if (!usuarioRepository.existsByNombreUsuario("admin")) {

                Usuario administrador = new Usuario(
                        "admin",
                        passwordEncoder().encode("Admin123"),
                        "Administrador del Sistema",
                        "ADMINISTRADOR"
                );

                usuarioRepository.save(administrador);

                System.out.println("==========================================");
                System.out.println("Usuario administrador creado correctamente");
                System.out.println("Usuario: admin");
                System.out.println("Contraseña: Admin123");
                System.out.println("==========================================");
            }
        };
    }
}