package com.tallerBiciV2.ventas.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TipoDTO {
    private Integer id;
    private String tipo;
    private List<Integer> boletaId;
}
