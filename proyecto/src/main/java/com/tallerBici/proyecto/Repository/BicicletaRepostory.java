package com.tallerBici.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Bicicleta;

@Repository
public interface BicicletaRepostory extends JpaRepository<Bicicleta, Integer> {

    List<Bicicleta> findByClienteId(Integer clienteId);

    List<Bicicleta> findByModeloId(Integer modeloId);

    List<Bicicleta> findByMaterial(String material);

    List<Bicicleta> findByMarcasId(Integer marcaId);  
}
