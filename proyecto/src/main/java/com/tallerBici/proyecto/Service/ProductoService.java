package com.tallerBici.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBici.proyecto.DTO.ProductoDTO;
import com.tallerBici.proyecto.Repository.ProductoRepository;
import com.tallerBici.proyecto.model.Boleta;
import com.tallerBici.proyecto.model.Producto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductoSinStock() {
        return productoRepository.findBystockLessThan(1);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.buscarPorNombre(nombre);
    }

    public List<Producto> buscarPorCodigoBarra(String codigoBarra){
        return productoRepository.findBycodigoBarras(codigoBarra);
    }

    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    private ProductoDTO convertirADTO(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCodigoBarras(producto.getCodigoBarras());
        
        if (producto.getBoletas() != null) {
            dto.setBoletas(producto.getBoletas().stream().map(Boleta::getId)
            .toList());
        }
        return dto;
    }

    public List<ProductoDTO> obtenerProductos(){
        return productoRepository.findAll().stream()
        .map(this::convertirADTO)
        .toList();
    }

    public ProductoDTO obtenerProductoDTOPorId(Integer id){
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Modelo no encontrado"));
        return convertirADTO(producto);
    }

    public String eliminarProducto(Integer id){
        try {
            Producto producto = productoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡No se puede eliminar producto no encontrado!"));
        productoRepository.delete(producto);
        return "¡El producto "+producto.getNombreProducto()+" ha sido eliminado con exito!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Producto actualizarProducto(Integer id, Producto productoActualizado){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException(
                            "No se encuentra producto"));
        if(producto != null){
            producto.setNombreProducto(productoActualizado.getNombreProducto());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            producto.setCodigoBarras(productoActualizado.getCodigoBarras());
            return productoRepository.save(producto);
        } 
        return null;
    }

    public List<ProductoDTO> obtenerProductoSinStockDTO(){
        return productoRepository.findBystockLessThan(1).stream()
        .map(this::convertirADTO).toList();
    }

    public List<ProductoDTO> buscarPorNombreDTO(String nombre){
        return productoRepository.buscarPorNombre(nombre).stream()
        .map(this::convertirADTO).toList();
    }

    public List<ProductoDTO> buscarPorCodigoDeBarraDTO(String codigo){
        return productoRepository.findBycodigoBarras(codigo).stream()
        .map(this::convertirADTO).toList();
    }
}
