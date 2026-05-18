package com.DuocucEcommerce.Inventario.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Inventario.Client.ProductoClient;
import com.DuocucEcommerce.Inventario.Dto.InventarioCreateDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioResponseDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioUpdateDTO;
import com.DuocucEcommerce.Inventario.Exception.BadRequestException;
import com.DuocucEcommerce.Inventario.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Inventario.Model.Inventario;
import com.DuocucEcommerce.Inventario.Repository.InventarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {
    private static final Logger log = LoggerFactory.getLogger(InventarioService.class);
    private final InventarioRepository repository;
    
    private final ProductoClient productoClient;
    
    public List<InventarioResponseDTO> listar() { 
        return repository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public InventarioResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerEntidad(id)); 
    }
    
    public InventarioResponseDTO buscarPorProducto(Integer productoId) { 
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado para producto " + productoId)); 
    }
    
    public InventarioResponseDTO crear(InventarioCreateDTO dto) { 
        productoClient.obtenerPorId(dto.getProductoId()); 
        validarStocks(dto.getStockDisponible(), 
        dto.getStockReservado(), 
        dto.getStockMinimo()); 
        Inventario inv = new Inventario(); 
        copiarDatos(dto, inv); 
        return toResponse(repository.save(inv)); 
    }
    
    public InventarioResponseDTO actualizar(Integer id, InventarioUpdateDTO dto) { 
        productoClient.obtenerPorId(dto.getProductoId()); 
        validarStocks(dto.getStockDisponible(), 
        dto.getStockReservado(), dto.getStockMinimo()); 
        Inventario inv = obtenerEntidad(id); 
        copiarDatos(dto, inv); 
        return toResponse(repository.save(inv)); 
    }
    
    public void eliminar(Integer id) { 
        repository.delete(obtenerEntidad(id)); 
    }
    
    public InventarioResponseDTO descontar(Integer productoId, Integer cantidad) { 
        if (cantidad == null || cantidad <= 0) { 
            throw new BadRequestException("Cantidad invalida"); } 
            Inventario inv = repository.findByProductoId(productoId).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado")); 
            if (inv.getStockDisponible() < cantidad) { 
                log.warn("Stock insuficiente para producto {}", productoId); throw new BadRequestException("Stock insuficiente"); } 
                inv.setStockDisponible(inv.getStockDisponible() - cantidad); return toResponse(repository.save(inv)); 
            }
    
    public InventarioResponseDTO reponer(Integer productoId, Integer cantidad) { 
        if (cantidad == null || cantidad <= 0) { throw new BadRequestException("Cantidad invalida"); } 
        Inventario inv = repository.findByProductoId(productoId).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado")); 
        inv.setStockDisponible(inv.getStockDisponible() + cantidad); 
        return toResponse(repository.save(inv)); 
    }
    
    private void validarStocks(Integer disponible, Integer reservado, Integer minimo) { 
        if (disponible < 0 || reservado < 0 || minimo < 0) { throw new BadRequestException("El stock no puede ser negativo"); } 
    }
    
    private Inventario obtenerEntidad(Integer id) { 
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id " + id)); 
    }
    
    private void copiarDatos(InventarioCreateDTO dto, Inventario inv) { 
        inv.setProductoId(dto.getProductoId()); 
        inv.setStockDisponible(dto.getStockDisponible()); 
        inv.setStockReservado(dto.getStockReservado()); 
        inv.setStockMinimo(dto.getStockMinimo()); 
    }
    
    private void copiarDatos(InventarioUpdateDTO dto, Inventario inv) { 
        inv.setProductoId(dto.getProductoId()); inv.setStockDisponible(dto.getStockDisponible()); inv.setStockReservado(dto.getStockReservado()); inv.setStockMinimo(dto.getStockMinimo()); }
    
    private InventarioResponseDTO toResponse(Inventario inv) { 
        return InventarioResponseDTO.builder()
        .id(inv.getId())
        .productoId(inv.getProductoId())
        .stockDisponible(inv.getStockDisponible())
        .stockReservado(inv.getStockReservado())
        .stockMinimo(inv.getStockMinimo())
        .build(); 
    }
}
