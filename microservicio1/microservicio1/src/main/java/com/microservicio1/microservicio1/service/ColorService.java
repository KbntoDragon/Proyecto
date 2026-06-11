package com.microservicio1.microservicio1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio1.microservicio1.DTO.ColorDTO;
import com.microservicio1.microservicio1.model.Color;
import com.microservicio1.microservicio1.repository.ColorRepository;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<ColorDTO> obtenerColores() {
        return colorRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public ColorDTO buscarPorId(Integer id) {
        Color color = colorRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Color no encontrado con id: " + id));
               return convertirADTO(color);
    }

    public Color guardarColor(Color color) {
        return colorRepository.save(color);
    }

    public String eliminar(Integer id) {
        try {
            Color color = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, el color no existe" + id));
                colorRepository.delete(color);
                return ("Color eliminado exitosamente");
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Color actualizarColor(Integer id, Color colorActualizado) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("No se puede encontrar el color."));
        if(color != null) {
            color.setNombre(colorActualizado.getNombre());
            return colorRepository.save(color);
        }
        return null;
    }

    public ColorDTO convertirADTO(Color color) {
        ColorDTO dto = new ColorDTO();
        dto.setId(color.getId());
        dto.setNombre(color.getNombre());

        if (color.getBicicletas() != null) {
            dto.setBicicletas(color.getBicicletas().stream()
                    .map(bicicletas -> bicicletas.getId())
                    .toList());
        }
        return dto;
    }
}
