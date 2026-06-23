package com.DuocucEcommerce.Carrito.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Carrito.Dto.ItemCarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Carrito.Model.ItemCarrito;
import com.DuocucEcommerce.Carrito.Repository.ItemCarritoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemCarritoService {
    private static final Logger log = LoggerFactory.getLogger(ItemCarritoService.class);
    private final ItemCarritoRepository repository;

    
    public List<ItemCarritoResponseDTO> listar() { 
        return repository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public ItemCarritoResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerEntidad(id)); 
    }
    
    public ItemCarritoResponseDTO crear(ItemCarritoCreateDTO dto) {
        log.info("Creando item carrito");
        ItemCarrito itemCarrito = new ItemCarrito();
        copiarDatos(dto, itemCarrito);
        return toResponse(repository.save(itemCarrito));
    }
    public ItemCarritoResponseDTO actualizar(Integer id, ItemCarritoUpdateDTO dto) {
        log.info("Actualizando item carrito con id {}", id);
        ItemCarrito itemCarrito = obtenerEntidad(id);
        copiarDatos(dto, itemCarrito);
        return toResponse(repository.save(itemCarrito));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private ItemCarrito obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ItemCarrito no encontrado con id " + id));
    }
    private void copiarDatos(ItemCarritoCreateDTO dto, ItemCarrito itemCarrito) {
        itemCarrito.setCarritoId(dto.getCarritoId());
        itemCarrito.setProductoId(dto.getProductoId());
        itemCarrito.setConfiguracionId(dto.getConfiguracionId());
        itemCarrito.setCantidad(dto.getCantidad());
        itemCarrito.setPrecioUnitario(dto.getPrecioUnitario());
        itemCarrito.setSubtotal(dto.getSubtotal());
    }
    private void copiarDatos(ItemCarritoUpdateDTO dto, ItemCarrito itemCarrito) {
        itemCarrito.setCarritoId(dto.getCarritoId());
        itemCarrito.setProductoId(dto.getProductoId());
        itemCarrito.setConfiguracionId(dto.getConfiguracionId());
        itemCarrito.setCantidad(dto.getCantidad());
        itemCarrito.setPrecioUnitario(dto.getPrecioUnitario());
        itemCarrito.setSubtotal(dto.getSubtotal());
    }
    private ItemCarritoResponseDTO toResponse(ItemCarrito itemCarrito) {
        return ItemCarritoResponseDTO.builder()
                .id(itemCarrito.getId())
                .carritoId(itemCarrito.getCarritoId())
                .productoId(itemCarrito.getProductoId())
                .configuracionId(itemCarrito.getConfiguracionId())
                .cantidad(itemCarrito.getCantidad())
                .precioUnitario(itemCarrito.getPrecioUnitario())
                .subtotal(itemCarrito.getSubtotal())
                .build();
    }
}