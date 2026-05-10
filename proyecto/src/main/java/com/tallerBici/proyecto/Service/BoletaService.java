package com.tallerBici.proyecto.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.BoletaDTO;
import com.tallerBici.proyecto.Repository.BoletaRepository;
import com.tallerBici.proyecto.Repository.ProductoRepository;
import com.tallerBici.proyecto.model.Boleta;
import com.tallerBici.proyecto.model.Producto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<BoletaDTO> obtenerBoletas(){
        return boletaRepository.findAll().stream()
                                .map(this::convertirADTO)
                                .toList();
    }

    public BoletaDTO buscarPorId(Integer id){
        Boleta boleta = boletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada con id: " + id));
        return convertirADTO(boleta);
    }

    public String eliminar(Integer id){
        try{
            Boleta boleta = boletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se puede eliminar, la boleta no existe con el id:" + id));
            boletaRepository.delete(boleta);
            return "Boleta eliminada con exito";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }
    
    public Boleta guardarBoleta(Boleta boleta){
        return boletaRepository.save(boleta);
    }

    public BoletaDTO convertirADTO(Boleta boleta){
        BoletaDTO dto = new BoletaDTO();
        dto.setId(boleta.getId());
        dto.setTotal(boleta.getTotal());

        if (boleta.getTipoPago() != null) {
            dto.setTipoPago(boleta.getTipoPago().toString());
        }

        if (boleta.getEmpleados() != null) {
            dto.setEmpleados(boleta.getEmpleados().stream()
                    .map(empleado -> empleado.getNombres()) 
                    .toList());
        }

        if (boleta.getProductos() != null) {
            dto.setProductos(boleta.getProductos().stream()
                    .map(producto -> producto.getNombreProducto())
                    .toList());
        }

        if (boleta.getRepuestos() != null) {
            dto.setRepuestos(boleta.getRepuestos().stream()
                    .map(repuesto -> repuesto.getNombreRepuesto())
                    .toList());
        }

        if (boleta.getServicios() != null) {
            dto.setServicios(boleta.getServicios().stream()
                    .map(servicio -> servicio.getNombreServicio())
                    .toList());
        }
        return dto;
    }

    public Boleta agregarProducto(Integer boletaId, Integer productoId){
        //buscar boleta
        Boleta boleta = boletaRepository.findById(boletaId)
        .orElseThrow(() -> new RuntimeException("No se encuentra, la boleta no existe con el id:" + boletaId));

        Producto producto = productoRepository
        .findById(productoId) .orElseThrow(() -> new RuntimeException("No se encuentra, el producto no existe con el id:" + productoId));

        boleta.getProductos().add(producto);

        boleta.setTotal(boleta.getTotal() + producto.getPrecio());

        return boletaRepository.save(boleta);
    } 
}
