package com.example.CRUD_Clientes.Service;

import com.example.CRUD_Clientes.Model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();
    private Long nextId = 1L;

    public List<Cliente> findAll() {
        return clientes;
    }

    public Cliente findById(Long id) {
        return clientes.stream().filter(c -> c.getIdCliente().equals(id)).findFirst().orElse(null);
    }

    public Cliente save(Cliente cliente) {
        cliente.setIdCliente(nextId++);
        clientes.add(cliente);
        return cliente;
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente existingCliente = findById(id);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setApellido(cliente.getApellido());
            existingCliente.setCedula(cliente.getCedula());
            existingCliente.setCorreo(cliente.getCorreo());
            existingCliente.setDireccion(cliente.getDireccion());
            existingCliente.setTelefono(cliente.getTelefono());
        }
        return existingCliente;
    }

    public boolean delete(Long id) {
        return clientes.removeIf(c -> c.getIdCliente().equals(id));
    }
}