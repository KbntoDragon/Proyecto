package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.BicicletaDTO;
import com.tallerBici.proyecto.Repository.BicicletaRepostory;
import com.tallerBici.proyecto.model.Bicicleta;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BicicletaService {

    @Autowired
    private  BicicletaRepostory bicicletaRepostory; 
    
    public List<BicicletaDTO> obtenerBicicletas(){
        return bicicletaRepostory.findAll().stream()
                                  .map(this::convertirADTO)
                                  .toList();
    }

    public BicicletaDTO buscarPorId(Integer id){
        Bicicleta bicicleta = bicicletaRepostory.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bicicleta no encontrada con id: " + id));
        return convertirADTO(bicicleta);
    }

    public String eliminar(Integer id){
        try{
            Bicicleta bicicleta = bicicletaRepostory.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar, la bicicleta no existe con el id:" + id));
            bicicletaRepostory.delete(bicicleta);
            return "Bicicleta eliminada con exito";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public Bicicleta guardarBicicleta(Bicicleta bicicleta){
        return bicicletaRepostory.save(bicicleta);
    }

    public Bicicleta actualizarBicicleta(Integer id, Bicicleta bicicleta){
        Bicicleta bici = bicicletaRepostory.findById(id).orElseThrow(() -> new RuntimeException("No se puede actualizar, la bicicleta no existe con los registros"));
        if(bicicleta.getMaterial() != null){
            bici.setMaterial(bicicleta.getMaterial());
        }
        return bicicletaRepostory.save(bici);
    }
    public List<BicicletaDTO> buscarPorCliente(Integer clienteId) {
        return bicicletaRepostory.findByClienteId(clienteId).stream()
                .map(this::convertirADTO)
                .toList();
    }
    
    public List<BicicletaDTO> buscarPorModelo(Integer modeloId){
        return bicicletaRepostory.findByModeloId(modeloId).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<BicicletaDTO> buscarPorMaterial(String material) {
        return bicicletaRepostory.findByMaterial(material).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<BicicletaDTO> buscarPorMarca(Integer marcaId) {
        return bicicletaRepostory.findByMarcasId(marcaId).stream()
                .map(this::convertirADTO)
                .toList();
    }
    
   private BicicletaDTO convertirADTO(Bicicleta bicicleta) {
        BicicletaDTO dto = new BicicletaDTO();
        dto.setId(bicicleta.getId());
        dto.setMaterial(bicicleta.getMaterial());
        return dto;
    }

}
