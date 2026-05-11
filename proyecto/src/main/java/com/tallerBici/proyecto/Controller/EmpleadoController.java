package com.tallerBici.proyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tallerBici.proyecto.DTO.EmpleadoDTO;
import com.tallerBici.proyecto.Service.EmpleadoService;
import com.tallerBici.proyecto.model.Empleado;


@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> todosLosEmpleados() {
        List<EmpleadoDTO> empleados = empleadoService.obtenerEmpleados();
        if (empleados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> buscarPorId(@PathVariable Integer id){
        EmpleadoDTO emplados = empleadoService.buscarPorId(id);
        return new ResponseEntity<>(emplados, HttpStatus.OK); 
    }

    @GetMapping ("nombres/{nombres}")
    public ResponseEntity<List<Empleado>> buscarPorNombres(@PathVariable String nombres) {
        List<Empleado> empleados = empleadoService.buscarPorNombre(nombres);
        if (empleados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado ) {
        try {
            Empleado guardado = empleadoService.guardarEmpleado(empleado);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<Empleado> editarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        try {
            Empleado editado = empleadoService.guardarEmpleado(empleado);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        try {
            Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Integer id) {
        String resultado = empleadoService.eliminar(id);
        
        if (resultado.contains("Eliminado exitosamente!")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}