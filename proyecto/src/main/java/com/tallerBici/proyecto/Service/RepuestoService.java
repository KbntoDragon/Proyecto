package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.RepuestoDTO;
import com.tallerBici.proyecto.Repository.RepuestoRepository;
import com.tallerBici.proyecto.model.Boleta;
import com.tallerBici.proyecto.model.Repuesto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RepuestoService {
    @Autowired
    private RepuestoRepository repuestoRepository;

    public List<Repuesto> obtenerRepuestosSinStock(){
        return repuestoRepository.findByStockRepuestoLessThan(1);
    }
    
    public List<Repuesto> buscarPorNombre(String nombre){
        return repuestoRepository.findByNombreRepuesto(nombre);
    }

    public List<Repuesto> buscarPorCodigoBarra(String codigoBarra){
        return repuestoRepository.findBycodigoBarras(codigoBarra);
    }

    public Repuesto guardarRepuesto(Repuesto repuesto){
        return repuestoRepository.save(repuesto);
    }

    private RepuestoDTO convertirADTO(Repuesto repuesto){
        RepuestoDTO dto = new RepuestoDTO();
        dto.setId(repuesto.getId());
        dto.setNombreRepuesto(repuesto.getNombreRepuesto());
        dto.setPrecio(repuesto.getPrecio());
        dto.setStockRepuesto(repuesto.getStockRepuesto());
        dto.setCodigoBarras(repuesto.getCodigoBarras());
        if (repuesto.getBoletas() != null) {
            dto.setBoletas(repuesto.getBoletas().stream().map(Boleta::getId)
            .toList());
        }
        return dto;
    }

    public List<RepuestoDTO> obtenerRepuestos(){
        return repuestoRepository.findAll().stream()
        .map(this::convertirADTO)
        .toList();
    }

    public RepuestoDTO obtenerRepuestoDTOPorId(Integer id){
        Repuesto repuesto = repuestoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));
        return convertirADTO(repuesto);
    }

    public String eliminarRepuesto(Integer id){
        try {
            Repuesto repuesto = repuestoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡No se puede eliminar repuesto no encontrado!"));
        repuestoRepository.delete(repuesto);
        return "¡El producto "+repuesto.getNombreRepuesto()+" ha sido eliminado con exito!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Repuesto actualizarRepuesto(Integer id, Repuesto repActualizado){
        Repuesto repuesto = repuestoRepository.findById(id).orElseThrow(() -> new RuntimeException(
                            "No se encuentra repuesto"));
        if(repuesto != null){
            repuesto.setNombreRepuesto(repActualizado.getNombreRepuesto());
            repuesto.setPrecio(repActualizado.getPrecio());
            repuesto.setStockRepuesto(repActualizado.getStockRepuesto());
            repuesto.setCodigoBarras(repActualizado.getCodigoBarras());
            return repuestoRepository.save(repuesto);
        }
        return null;
    }

    public List<RepuestoDTO> obtenerRepuestosSinStockDTO(){
        return repuestoRepository.findByStockRepuestoLessThan(1).stream()
        .map(this::convertirADTO).toList();
    }

    public List<RepuestoDTO> buscarPorNombreDTO(String nombre){
        return repuestoRepository.findByNombreRepuesto(nombre).stream()
        .map(this::convertirADTO).toList();
    }
    
    public List<RepuestoDTO> buscarPorCodigoBarraDTO(String codigo){
        return repuestoRepository.findBycodigoBarras(codigo).stream()
        .map(this::convertirADTO).toList();
    }


}
