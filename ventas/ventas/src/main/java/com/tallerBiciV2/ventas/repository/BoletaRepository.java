package com.tallerBiciV2.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBiciV2.ventas.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository <Boleta, Integer> {
}
