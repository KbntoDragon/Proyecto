package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.ServicioDTO;
import com.tallerBici.proyecto.Repository.ServicioRepository;
import com.tallerBici.proyecto.model.Boleta;
import com.tallerBici.proyecto.model.Servicio;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    public Servicio guardarServicio(Servicio servicio){
        return servicioRepository.save(servicio);
    }

    public List<Servicio> buscarPorNombre(String nombre){
        return servicioRepository.findBynombreServicioContainingIgnoreCase(nombre);
    }

    private ServicioDTO convertirADTO(Servicio servicio){
        ServicioDTO dto = new ServicioDTO();
        dto.setId(servicio.getId());
        dto.setNombreServicio(servicio.getNombreServicio());
        dto.setDescServicio(servicio.getDescServicio());
        dto.setValorDelServicio(servicio.getValorDelServicio());

        if(servicio.getBoletas() != null){
            dto.setBoletas(servicio.getBoletas().stream().map(Boleta::getId)
            .toList());
        }
        return dto;
    }
    
    public List<ServicioDTO> obtenerServicios(){
        return servicioRepository.findAll().stream()
        .map(this::convertirADTO)
        .toList();
    }

    public ServicioDTO obtenerServicioDTOPorId(Integer id){
        Servicio servicio = servicioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        return convertirADTO(servicio);
    }

    public String eliminarServicio(Integer id){
        try {
            Servicio servicio = servicioRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡No se puede eliminar servicio no encontrado!"));
        servicioRepository.delete(servicio);
        return "¡El servicio "+servicio.getNombreServicio()+" ha sido eliminado con exito!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Servicio actualizarServicio(Integer id, Servicio servicioActualizado){
        Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new RuntimeException(
                            "Servicio no encontrado"));
        if (servicio != null) {
            servicio.setNombreServicio(servicioActualizado.getNombreServicio());
            servicio.setDescServicio(servicioActualizado.getDescServicio());
            servicio.setValorDelServicio(servicioActualizado.getValorDelServicio());
            return servicioRepository.save(servicio);
        }
        return null;
    }

    public List<ServicioDTO> buscarPorNombreDTO(String nombre){
        return servicioRepository.findBynombreServicioContainingIgnoreCase(nombre).stream()
        .map(this::convertirADTO).toList();
    }

    
}
