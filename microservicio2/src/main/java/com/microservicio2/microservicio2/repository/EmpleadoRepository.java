package com.microservicio2.microservicio2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio2.microservicio2.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

    List<Empleado> findByNombresContainingIgnoreCase(String nombres);

}
