package com.tallerBici.proyecto.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boletas")
public class Boleta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
    private Double total;

  @ManyToMany
  @JoinTable(name = "boletas_empleados",
      joinColumns = @JoinColumn(name = "boleta_id"),
      inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleados;

  @ManyToOne
  @JoinColumn (name = "TipoPago_id")
  private TipoPago tipoPago;

  @ManyToMany
  @JoinTable(name = "boletas_productos",
    joinColumns = @JoinColumn(name = "boleta_id"),
    inverseJoinColumns = @JoinColumn(name = "producto_id")
  )
  private List<Producto> productos;

  @ManyToMany
  @JoinTable(name = "boletas_repuestos",
    joinColumns = @JoinColumn(name = "boleta_id"),
    inverseJoinColumns = @JoinColumn(name = "repuesto_id")
  )
  private List<Repuesto> repuestos;


  @ManyToMany
  @JoinTable(name = "boletas_servicios",
    joinColumns = @JoinColumn(name = "boleta_id"),
    inverseJoinColumns = @JoinColumn(name = "servicio_id")
  )
  private List<Servicio> servicios;
  
}
