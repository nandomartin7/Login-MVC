package com.example.CRUD_Clientes.Service;

import com.example.CRUD_Clientes.Model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private Long nextId = 1L;

    public Usuario register(Usuario usuario, String encodedPassword){
        usuario.setPassword(encodedPassword);
        usuario.setId(nextId++);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario findByUsername(String username){
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}