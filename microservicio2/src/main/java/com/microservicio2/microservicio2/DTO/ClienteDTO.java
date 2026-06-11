package com.microservicio2.microservicio2.DTO;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private Integer bicicletaId;

}
