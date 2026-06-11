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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio2.microservicio2.DTO.EmpleadoDTO;
import com.microservicio2.microservicio2.model.Empleado;
import com.microservicio2.microservicio2.service.EmpleadoService;

@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmpleadoDTO empleado;

    @BeforeEach
    void setUp() {
        empleado.setId(1);
        empleado.setNombres("Gustavo");
        empleado.setApellidos("Hernandez");
    }

    @Test
    public void testGetAllEmpleados() throws Exception {
        when(empleadoService.findAll()).thenReturn(List.of(empleado));

        mockMvc.perform(get("/api/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombres").value("Gustavo"))
                .andExpect(jsonPath("$[0].apellidos").value("Hernandez"));
    }

    @Test
    public void testGetEmpleadoById() throws Exception {
        when(empleadoService.buscarPorId(1)).thenReturn(empleado);

        mockMvc.perform(get("/api/empleados/1"))
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$.id").value(1)) // Verifica que el id del objeto devuelto sea 1
                .andExpect(jsonPath("$.nombres").value("Gustavo"))
                .andExpect(jsonPath("$.apellidos").value("Hernandez"));
    }

    @Test
    public void testCreateEmpleado() throws Exception {
        when(empleadoService.guardarEmpleado(any(Empleado.class))).thenReturn(empleado);

        mockMvc.perform(post("/api/empleados")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.id").value(1)) 
                .andExpect(jsonPath("$.nombres").value("ClaGustavoudio")) 
                .andExpect(jsonPath("$.apellidos").value("Hernandez"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        
        when(empleadoService.guardarEmpleado(any(Empleado.class))).thenReturn(empleado);

        mockMvc.perform(put("/api/empleados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado))) 
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.id").value(1)) 
                .andExpect(jsonPath("$.run").value("Gustavo")) 
                .andExpect(jsonPath("$.nombres").value("Hernandez")); 
    }

    @Test
    public void testDeleteEmpleado() throws Exception {
        doNothing().when(empleadoService).eliminarPorId(1);

        mockMvc.perform(delete("/api/empleados/1"))
                .andExpect(status().isOk()); 
        verify(empleadoService, times(1)).eliminarPorId(1);
    }

}
