package com.tallerBici.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Repuesto;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer>{
    List<Repuesto>findByNombreRepuesto(String nombreRepuesto);
    List<Repuesto> findByStockRepuestoLessThan(Integer stockRepuesto);
    List<Repuesto> findBycodigoBarras(String codigoBarras);
}
