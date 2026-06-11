package com.microservicio2.microservicio2.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio2.microservicio2.DTO.ClienteDTO;
import com.microservicio2.microservicio2.model.Cliente;
import com.microservicio2.microservicio2.service.ClienteService;

import org.springframework.http.MediaType;

@WebMvcTest(ClienteController.class) // Indica que se está probando el controlador de Cliente
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc; // Proporciona una manera de realizar peticiones HTTP en las pruebas

    @MockitoBean
    private ClienteService clienteService; // Crea un mock del servicio de Cliente

    @Autowired
    private ObjectMapper objectMapper; // Se usa para convertir objetos Java a JSON y viceversa

    private ClienteDTO cliente;

    @BeforeEach
    void setUp() {
        // Configura un objeto Cliente de ejemplo antes de cada prueba
        cliente = new ClienteDTO();
        cliente.setId(1);
        cliente.setNombres("Claudio");
        cliente.setApellidos("Salgado");
    }

    @Test
    public void testGetAllClientes() throws Exception {
        when(clienteService.findAll()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombres").value("Claudio"))
                .andExpect(jsonPath("$[0].apellidos").value("Salgado"));
    }

    @Test
    public void testGetClienteById() throws Exception {
        when(clienteService.buscarPorId(1)).thenReturn(cliente);

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$.id").value(1)) // Verifica que el id del objeto devuelto sea 1
                .andExpect(jsonPath("$.nombres").value("Claudio"))
                .andExpect(jsonPath("$.apellidos").value("Salgado"));
    }

    @Test
    public void testCreateCliente() throws Exception {
        when(clienteService.guardarCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.id").value(1)) 
                .andExpect(jsonPath("$.nombres").value("Claudio")) 
                .andExpect(jsonPath("$.apellidos").value("Salgado"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        
        when(clienteService.guardarCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente))) 
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.id").value(1)) 
                .andExpect(jsonPath("$.run").value("Claudio")) 
                .andExpect(jsonPath("$.nombres").value("Salgado")); 
    }

    @Test
    public void testDeleteCliente() throws Exception {
        doNothing().when(clienteService).eliminarPorId(1);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isOk()); 
        verify(clienteService, times(1)).eliminarPorId(1);
    }

}
