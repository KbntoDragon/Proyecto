package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.EmpleadoDTO;
import com.tallerBici.proyecto.Repository.EmpleadoRepository;
import com.tallerBici.proyecto.model.Empleado;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoRepository.findAll().stream()
                                .map(this::convertirADTO)
                                .toList();
    }

    public EmpleadoDTO buscarPorId(Integer id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));
        return convertirADTO(empleado);
    }

    public List<Empleado> buscarPorNombre(String nombres) {
        List<Empleado> empleados = empleadoRepository.findByNombresContainingIgnoreCase(nombres);

        if (empleados.isEmpty()) {
            throw new RuntimeException("No existen empleados con el nombre: " + nombres);
        }
        return empleados;
    }
    
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public String eliminar(Integer id) {
        try {
            Empleado empleado = empleadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar, el empleado no existe con id: " + id));
            empleadoRepository.delete(empleado);
            return ("Empleado eliminado exitosamente");     

        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Empleado actualizarEmpleado(Integer id, Empleado empleadoActualizado) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encuentra el empleado."));
        if(empleado != null) {
            empleado.setNombres(empleadoActualizado.getNombres());
            empleado.setApellidos(empleadoActualizado.getApellidos());
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    private EmpleadoDTO convertirADTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombres(empleado.getNombres());
        dto.setApellidos(empleado.getApellidos());

        if (empleado.getBoletas() != null && !empleado.getBoletas().isEmpty()) {
            dto.setBoletas(empleado.getBoletas().stream().map(boleta -> boleta.getId()).toList());
        }
        return dto;
    }

}
