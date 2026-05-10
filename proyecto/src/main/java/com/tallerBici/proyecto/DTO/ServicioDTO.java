package com.tallerBici.proyecto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ServicioDTO {
    private Integer id;
    private String nombreServicio;
    private String descServicio;
    private Double valorDelServicio;
    private List<Integer> boletas;
}
