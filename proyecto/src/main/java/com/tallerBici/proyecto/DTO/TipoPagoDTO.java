package com.tallerBici.proyecto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TipoPagoDTO {
    private Integer id;
    private String tipo;
    private List<Integer> boletas;
}
