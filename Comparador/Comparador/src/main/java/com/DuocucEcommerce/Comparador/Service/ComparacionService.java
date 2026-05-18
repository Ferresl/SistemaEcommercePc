package com.DuocucEcommerce.Comparador.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Comparador.Client.CategoriaClient;
import com.DuocucEcommerce.Comparador.Client.ProductoClient;
import com.DuocucEcommerce.Comparador.Client.ProductoResponseDTO;
import com.DuocucEcommerce.Comparador.Client.UsuarioClient;
import com.DuocucEcommerce.Comparador.Dto.AgregarItemComparacionDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Exception.BadRequestException;
import com.DuocucEcommerce.Comparador.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Comparador.Model.Comparacion;
import com.DuocucEcommerce.Comparador.Model.ItemComparacion;
import com.DuocucEcommerce.Comparador.Repository.ComparacionRepository;
import com.DuocucEcommerce.Comparador.Repository.ItemComparacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComparacionService {
    private final ComparacionRepository comparacionRepository;
    private final ItemComparacionRepository itemRepository;
    private final UsuarioClient usuarioClient;
    private final CategoriaClient categoriaClient;
    private final ProductoClient productoClient;
    public ComparacionResponseDTO buscarPorId(Integer id) { Comparacion c = obtener(id); validarMinimo(c.getId()); return toResponse(c); }
    public List<ComparacionResponseDTO> listarPorUsuario(Integer usuarioId) { return comparacionRepository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList(); }
    public ComparacionResponseDTO crear(ComparacionCreateDTO dto) { usuarioClient.validar(dto.getUsuarioId()); categoriaClient.validar(dto.getCategoriaId()); Comparacion c = Comparacion.builder().usuarioId(dto.getUsuarioId()).categoriaId(dto.getCategoriaId()).build(); return toResponse(comparacionRepository.save(c)); }
    public ComparacionResponseDTO agregarItem(Integer comparacionId, AgregarItemComparacionDTO dto) { Comparacion c = obtener(comparacionId); List<ItemComparacion> actuales = itemRepository.findByComparacionId(comparacionId); if (actuales.size() >= 4) { throw new BadRequestException("Solo se pueden comparar hasta 4 productos"); } ProductoResponseDTO producto = productoClient.obtener(dto.getProductoId()); if (!producto.getCategoriaId().equals(c.getCategoriaId())) { throw new BadRequestException("Los productos deben ser de la misma categoria"); } ItemComparacion item = ItemComparacion.builder().comparacionId(comparacionId).productoId(dto.getProductoId()).build(); itemRepository.save(item); return toResponse(c); }
    public void eliminarItem(Integer itemId) { itemRepository.delete(itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item no encontrado"))); }
    private void validarMinimo(Integer comparacionId) { if (itemRepository.findByComparacionId(comparacionId).size() < 2) { throw new BadRequestException("Debe agregar al menos 2 productos para comparar"); } }
    private Comparacion obtener(Integer id) { return comparacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comparacion no encontrada con id " + id)); }
    private ItemComparacionResponseDTO itemResponse(ItemComparacion item) { return ItemComparacionResponseDTO.builder().id(item.getId()).comparacionId(item.getComparacionId()).productoId(item.getProductoId()).build(); }
    private ComparacionResponseDTO toResponse(Comparacion c) { return ComparacionResponseDTO.builder().id(c.getId()).usuarioId(c.getUsuarioId()).categoriaId(c.getCategoriaId()).items(itemRepository.findByComparacionId(c.getId()).stream().map(this::itemResponse).toList()).build(); }
}