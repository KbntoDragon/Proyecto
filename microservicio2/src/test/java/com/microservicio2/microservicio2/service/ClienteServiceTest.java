package com.microservicio2.microservicio2.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.microservicio2.microservicio2.DTO.ClienteDTO;
import com.microservicio2.microservicio2.model.Cliente;
import com.microservicio2.microservicio2.repository.ClienteRepository;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockitoBean
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(1, "Claudio", "Salgado", "22876988-k", "claudiosal@gmail.com", "+56 9 7744 6521")));

        List<ClienteDTO> clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Cliente cliente = new Cliente(id, "Claudio", "Salgado", "22876988-k", "claudiosal@gmail.com", "+56 9 7744 6521");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        ClienteDTO found = clienteService.buscarPorId(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente(1, "Claudio", "Salgado", "22876988-k", "claudiosal@gmail.com", "+56 9 7744 6521");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteDTO saved = clienteService.guardarCliente(cliente);
        assertNotNull(saved);
        assertEquals("Claudio", saved.getNombres());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        
        doNothing().when(clienteRepository).deleteById(id);
        
        clienteService.eliminarPorId(id);
        
        verify(clienteRepository, times(1)).deleteById(id);
    }

}
