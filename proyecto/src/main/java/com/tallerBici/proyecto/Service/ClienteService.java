package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.ClienteDTO;
import com.tallerBici.proyecto.Repository.ClienteRepository;
import com.tallerBici.proyecto.model.Cliente;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obtenerClientes(){
        return clienteRepository.findAll().stream()
                                .map(this::convertirADTO)
                                .toList();
    }

    public ClienteDTO buscarPorId(Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrada con id: " + id));
        return convertirADTO(cliente);
    }

    public String eliminar(Integer id){
        try{
            Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar, el cliente no existe con el id:" + id));
            clienteRepository.delete(cliente);
            return "Cliente eliminado con exito";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }
    
    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public ClienteDTO buscarPorCorreo(String correo) {
    return clienteRepository.findByCorreo(correo)
            .map(this::convertirADTO)
            .orElseThrow(() -> new RuntimeException("No se encontró un cliente con el correo: " + correo));
}
    public List<Cliente> buscarPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombres(nombre);
        if (clientes.isEmpty()) {
          throw new RuntimeException("No se encontraron clientes que coincidan con: " + nombre);
    }
        return clientes;
    }

    public ClienteDTO convertirADTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombres(cliente.getNombres());
        dto.setApellidos(cliente.getApellidos());
        dto.setCorreo(cliente.getCorreo());
        dto.setTelefono(cliente.getTelefono());

        if (cliente.getBicicletas() != null && !cliente.getBicicletas().isEmpty()) {
            dto.setBicicletaId(cliente.getBicicletas().get(0).getId());
        }
        return dto;
    }
}