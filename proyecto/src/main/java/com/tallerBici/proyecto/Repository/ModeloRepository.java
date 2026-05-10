package com.tallerBici.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Modelo;
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

}
