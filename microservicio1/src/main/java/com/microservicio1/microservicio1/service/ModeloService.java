package com.microservicio1.microservicio1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio1.microservicio1.repository.ModeloRepository;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;
}
