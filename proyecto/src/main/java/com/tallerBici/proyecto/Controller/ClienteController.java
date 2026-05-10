package com.tallerBici.proyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tallerBici.proyecto.DTO.ClienteDTO;
import com.tallerBici.proyecto.Service.ClienteService;
import com.tallerBici.proyecto.model.Cliente;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ClienteDTO> obtenerClientes(){
        List<ClienteDTO> clientes = clienteService.obtenerClientes();
        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id){
        return new ResponseEntity<>(clienteService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id){
        String mensajeAlerta = clienteService.eliminar(id);
        return new ResponseEntity<>(mensajeAlerta, HttpStatus.OK);
    }
    
    @GetMapping("/correo/{correo}")
    public ResponseEntity<ClienteDTO> buscarPorCorreo(@PathVariable String correo) {
        ClienteDTO clienteDTO = clienteService.buscarPorCorreo(correo);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Cliente>> buscarPorNombre(@PathVariable String nombre) {
        List<Cliente> clientes = clienteService.buscarPorNombre(nombre);
        
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

}
