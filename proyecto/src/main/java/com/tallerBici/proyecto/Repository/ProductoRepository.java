package com.tallerBici.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tallerBici.proyecto.model.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findBystockLessThan(Integer stock);
    //List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);
    List<Producto> findBycodigoBarras(String codigoBarra);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombreProducto) = LOWER(:nombre)")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);
}
