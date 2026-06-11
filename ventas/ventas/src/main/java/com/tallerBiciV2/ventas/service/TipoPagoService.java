package com.tallerBiciV2.ventas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerBiciV2.ventas.model.TipoPago;
import com.tallerBiciV2.ventas.repository.TipoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoPagoService {
    @Autowired
    private TipoPagoRepository tipopagoRepository;

    public TipoPago guardarTipoPago(TipoPago tipoPago){
        return tipopagoRepository.save(tipoPago);
    }

    private TipoPagoDTO convertirADTO(TipoPago tipoPago){
        TipoPagoDTO dto = new TipoPagoDTO();
        dto.setId(tipoPago.getId());
        dto.setTipo(tipoPago.getTipo());
        if (tipoPago.getBoletas() != null) {
            dto.setBoletas(tipoPago.getBoletas().stream().map(Boleta::getId)
            .toList());
        }
        return dto;
    }

    public List<TipoPagoDTO> obtenerTipoPago(){
        return tipopagoRepository.findAll().stream()
        .map(this::convertirADTO)
        .toList();
    }

    public TipoPagoDTO obtenerTipoPagoDTOPorId(Integer id){
        TipoPago tipoPago = tipopagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado"));
        return convertirADTO(tipoPago);
    }

    public String eliminarTipoPago(Integer id){
        try {
            TipoPago tipoPago = tipopagoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡No se puede eliminar el tipo de pago no encontrado!"));
        tipopagoRepository.delete(tipoPago);
        return "¡El producto "+tipoPago.getTipo()+" ha sido eliminado con exito!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public TipoPago actualizarTipoPago(Integer id, TipoPago tipoPagoActualizado){
        TipoPago tipoPago = tipopagoRepository.findById(id).orElseThrow(() -> new RuntimeException(
                            "No se encuentra el tipo de pago"));
        if(tipoPago != null){
            tipoPago.setTipo(tipoPagoActualizado.getTipo());
            return tipopagoRepository.save(tipoPago);
        }
        return null;
    }
}
