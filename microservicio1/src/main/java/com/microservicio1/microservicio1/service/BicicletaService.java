package com.microservicio1.microservicio1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio1.microservicio1.DTO.BicicletaDTO;
import com.microservicio1.microservicio1.model.Bicicleta;
import com.microservicio1.microservicio1.repository.BicicletaRepository;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository bicicletaRepository;
    
    public List<BicicletaDTO> obtenerTodas() {
        List<BicicletaDTO> listaDTOs = new ArrayList<>();
        List<Bicicleta> bicicletasReales = bicicletaRepository.findAll();
        for (Bicicleta bici : bicicletasReales) {
            listaDTOs.add(convertirADTO(bici));
        }
        return listaDTOs;
    }

    public BicicletaDTO buscarPorId(Integer id) {
        Bicicleta bici = bicicletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bicicleta no encontrada en los archivos"));
        return convertirADTO(bici);
    }

    public BicicletaDTO guardar(Bicicleta nuevaBicicleta) {
        Bicicleta guardada = bicicletaRepository.save(nuevaBicicleta);
        return convertirADTO(guardada);
    }

    
    private BicicletaDTO convertirADTO(Bicicleta bici) {
        BicicletaDTO dto = new BicicletaDTO();
        dto.setId(bici.get());
        dto.setMaterial(bici.getMaterial());
        return dto;
    }
}
