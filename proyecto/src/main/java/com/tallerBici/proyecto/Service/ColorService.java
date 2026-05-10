package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.Repository.ColorRepository;
import com.tallerBici.proyecto.model.Color;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> obtenerColores() {
        return colorRepository.findAll();
    }

    public Color buscarPorId(Integer id) {
        return colorRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Color no encontrado con id: " + id));
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
    

}
