package com.tallerBici.proyecto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class EmpleadoDTO {
    private Integer id;
    private String nombres;
    private String apellidos;
    private List<Integer> boletas;
}
