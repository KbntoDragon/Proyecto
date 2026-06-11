package com.microservicio2.microservicio2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio2.microservicio2.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    Optional<Cliente> findByCorreo(String correo);

    List<Cliente> findByNombres(String nombres);


    

}
