package com.tallerBici.proyecto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class BicicletaDTO {
    private Integer id;
    private String material;
    private String clienteNombre;
    private String modeloNombre;
    private List<String> colores;
    private List<String> marcas;

}
