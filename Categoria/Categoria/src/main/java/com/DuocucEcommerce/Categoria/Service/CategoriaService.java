package com.DuocucEcommerce.Categoria.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Categoria.Dto.CategoriaCreateDTO;
import com.DuocucEcommerce.Categoria.Dto.CategoriaResponseDTO;
import com.DuocucEcommerce.Categoria.Dto.CategoriaUpdateDTO;
import com.DuocucEcommerce.Categoria.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Categoria.Model.Categoria;
import com.DuocucEcommerce.Categoria.Repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository repository;

    
    public List<CategoriaResponseDTO> listar() { 
        return repository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public CategoriaResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerEntidad(id)); 
    }
    
    public CategoriaResponseDTO crear(CategoriaCreateDTO dto) {
        log.info("Creando categoria");
        Categoria categoria = new Categoria();
        copiarDatos(dto, categoria);
        return toResponse(repository.save(categoria));
    }
    public CategoriaResponseDTO actualizar(Integer id, CategoriaUpdateDTO dto) {
        log.info("Actualizando categoria con id {}", id);
        Categoria categoria = obtenerEntidad(id);
        copiarDatos(dto, categoria);
        return toResponse(repository.save(categoria));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private Categoria obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado con id " + id));
    }
    private void copiarDatos(CategoriaCreateDTO dto, Categoria categoria) {
        categoria.setNombre(dto.getNombre());
        categoria.setTipoProducto(dto.getTipoProducto());
    }
    private void copiarDatos(CategoriaUpdateDTO dto, Categoria categoria) {
        categoria.setNombre(dto.getNombre());
        categoria.setTipoProducto(dto.getTipoProducto());
    }
    private CategoriaResponseDTO toResponse(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .tipoProducto(categoria.getTipoProducto())
                .build();
    }
}
