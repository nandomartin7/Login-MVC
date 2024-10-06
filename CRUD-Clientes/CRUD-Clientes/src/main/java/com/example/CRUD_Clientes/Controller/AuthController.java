package com.example.CRUD_Clientes.Controller;

import com.example.CRUD_Clientes.Model.Usuario;
import com.example.CRUD_Clientes.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;
     private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario existingUser = usuarioService.findByUsername(usuario.getUsername());
        if (existingUser != null && passwordEncoder.matches(usuario.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("Página de inicio de sesión");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword()); // Encriptar la contraseña
        usuarioService.register(usuario, encodedPassword); // Pasar la contraseña encriptada
        return ResponseEntity.ok("Registro exitoso");
    }
}