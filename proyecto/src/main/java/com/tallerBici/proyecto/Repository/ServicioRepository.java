package com.tallerBici.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Servicio;
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>{

    List<Servicio>findBynombreServicioContainingIgnoreCase(String nombre);
}
