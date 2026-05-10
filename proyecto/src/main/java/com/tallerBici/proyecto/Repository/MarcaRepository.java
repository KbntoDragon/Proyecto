package com.tallerBici.proyecto.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Marca;
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
     Optional<Marca> findByNombre(String nombre);
}
