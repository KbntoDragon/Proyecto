package com.tallerBici.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.TipoPago;

@Repository
public interface TipopagoRepository extends JpaRepository<TipoPago, Integer> {

}
