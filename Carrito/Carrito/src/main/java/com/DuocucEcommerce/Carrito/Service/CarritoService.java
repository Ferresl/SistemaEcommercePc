package com.DuocucEcommerce.Carrito.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Carrito.Client.InventarioClient;
import com.DuocucEcommerce.Carrito.Client.InventarioResponseDTO;
import com.DuocucEcommerce.Carrito.Client.ProductoClient;
import com.DuocucEcommerce.Carrito.Client.ProductoResponseDTO;
import com.DuocucEcommerce.Carrito.Client.UsuarioClient;
import com.DuocucEcommerce.Carrito.Dto.ActualizarCantidadItemDTO;
import com.DuocucEcommerce.Carrito.Dto.AgregarItemCarritoDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.CarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Exception.BadRequestException;
import com.DuocucEcommerce.Carrito.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Carrito.Model.Carrito;
import com.DuocucEcommerce.Carrito.Model.ItemCarrito;
import com.DuocucEcommerce.Carrito.Repository.CarritoRepository;
import com.DuocucEcommerce.Carrito.Repository.ItemCarritoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoService {
    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemRepository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;
    private final InventarioClient inventarioClient;

    public List<CarritoResponseDTO> listar() { return carritoRepository.findAll().stream().map(this::toResponse).toList(); }
    public CarritoResponseDTO buscarPorId(Integer id) { return toResponse(obtenerCarrito(id)); }
    public CarritoResponseDTO buscarPorUsuario(Integer usuarioId) { return carritoRepository.findByUsuarioId(usuarioId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado")); }
    public CarritoResponseDTO crear(CarritoCreateDTO dto) { usuarioClient.obtenerPorId(dto.getUsuarioId()); Carrito carrito = Carrito.builder().usuarioId(dto.getUsuarioId()).subtotal(BigDecimal.ZERO).total(BigDecimal.ZERO).build(); return toResponse(carritoRepository.save(carrito)); }
    public CarritoResponseDTO actualizar(Integer id, CarritoUpdateDTO dto) { usuarioClient.obtenerPorId(dto.getUsuarioId()); Carrito carrito = obtenerCarrito(id); carrito.setUsuarioId(dto.getUsuarioId()); return toResponse(carritoRepository.save(carrito)); }
    public void eliminar(Integer id) { carritoRepository.delete(obtenerCarrito(id)); }
    public CarritoResponseDTO agregarItem(Integer carritoId, AgregarItemCarritoDTO dto) { Carrito carrito = obtenerCarrito(carritoId); ProductoResponseDTO producto = productoClient.obtenerPorId(dto.getProductoId()); validarStock(dto.getProductoId(), dto.getCantidad()); BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(dto.getCantidad())); ItemCarrito item = ItemCarrito.builder().carritoId(carrito.getId()).productoId(dto.getProductoId()).configuracionId(dto.getConfiguracionId()).cantidad(dto.getCantidad()).precioUnitario(producto.getPrecio()).subtotal(subtotal).build(); itemRepository.save(item); recalcular(carrito.getId()); return buscarPorId(carrito.getId()); }
    public CarritoResponseDTO actualizarCantidad(Integer itemId, ActualizarCantidadItemDTO dto) { ItemCarrito item = obtenerItem(itemId); validarStock(item.getProductoId(), dto.getCantidad()); item.setCantidad(dto.getCantidad()); item.setSubtotal(item.getPrecioUnitario().multiply(BigDecimal.valueOf(dto.getCantidad()))); itemRepository.save(item); recalcular(item.getCarritoId()); return buscarPorId(item.getCarritoId()); }
    public void eliminarItem(Integer itemId) { ItemCarrito item = obtenerItem(itemId); Integer carritoId = item.getCarritoId(); itemRepository.delete(item); recalcular(carritoId); }
    public void vaciar(Integer carritoId) { List<ItemCarrito> items = itemRepository.findByCarritoId(carritoId); itemRepository.deleteAll(items); recalcular(carritoId); }
    private void validarStock(Integer productoId, Integer cantidad) { InventarioResponseDTO inv = inventarioClient.obtenerPorProducto(productoId); if (inv.getStockDisponible() < cantidad) { throw new BadRequestException("Stock insuficiente"); } }
    private void recalcular(Integer carritoId) { Carrito carrito = obtenerCarrito(carritoId); BigDecimal total = itemRepository.findByCarritoId(carritoId).stream().map(ItemCarrito::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add); carrito.setSubtotal(total); carrito.setTotal(total); carritoRepository.save(carrito); }
    private Carrito obtenerCarrito(Integer id) { return carritoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado con id " + id)); }
    private ItemCarrito obtenerItem(Integer id) { return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item no encontrado con id " + id)); }
    private ItemCarritoResponseDTO itemResponse(ItemCarrito item) { return ItemCarritoResponseDTO.builder().id(item.getId()).carritoId(item.getCarritoId()).productoId(item.getProductoId()).configuracionId(item.getConfiguracionId()).cantidad(item.getCantidad()).precioUnitario(item.getPrecioUnitario()).subtotal(item.getSubtotal()).build(); }
    private CarritoResponseDTO toResponse(Carrito carrito) { return CarritoResponseDTO.builder().id(carrito.getId()).usuarioId(carrito.getUsuarioId()).subtotal(carrito.getSubtotal()).total(carrito.getTotal()).items(itemRepository.findByCarritoId(carrito.getId()).stream().map(this::itemResponse).toList()).build(); }
}
