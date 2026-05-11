package com.tallerBici.proyecto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ColorDTO {
    private Integer id;
    private String nombre;
    private List<Integer> bicicletas;

}
