package com.microservicio1.microservicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio1.microservicio1.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

}
