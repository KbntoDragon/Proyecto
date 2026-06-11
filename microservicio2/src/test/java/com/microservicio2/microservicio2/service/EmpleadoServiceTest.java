package com.microservicio2.microservicio2.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.microservicio2.microservicio2.DTO.EmpleadoDTO;
import com.microservicio2.microservicio2.model.Empleado;
import com.microservicio2.microservicio2.repository.EmpleadoRepository;

@SpringBootTest
public class EmpleadoServiceTest {

    @Autowired
    private EmpleadoService empleadoService;

    @MockitoBean
    private EmpleadoRepository empleadoRepository;

    @Test
    public void testFindAll() {
        when(empleadoRepository.findAll()).thenReturn(List.of(new Empleado(1, "Gustavo", "Hernandez")));

        List<EmpleadoDTO> empleados = empleadoService.findAll();
        assertNotNull(empleados);
        assertEquals(1, empleados.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Empleado empleado = new Empleado(id, "Gustavo", "Hernandez");

        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado));

        EmpleadoDTO found = empleadoService.buscarPorId(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Empleado empleado = new Empleado(1, "Gustavo", "Hernandez");

        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        EmpleadoDTO saved = empleadoService.guardarEmpleado(empleado);
        assertNotNull(saved);
        assertEquals("Gustavo", saved.getNombres());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        
        doNothing().when(empleadoRepository).deleteById(id);
        
        empleadoService.eliminarPorId(id);
        
        verify(empleadoRepository, times(1)).deleteById(id);
    }

}
