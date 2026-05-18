package DuocucEcommerce.Producto.Service;


import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.FuentePoderDTO.FuentePoderCreateDTO;
import DuocucEcommerce.Producto.Dto.FuentePoderDTO.FuentePoderResponseDTO;
import DuocucEcommerce.Producto.Dto.FuentePoderDTO.FuentePoderUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.FuentePoder;
import DuocucEcommerce.Producto.Repository.FuentePoderRepository;

@Service
@RequiredArgsConstructor
public class FuentePoderService {
    private static final Logger log = LoggerFactory.getLogger(FuentePoderService.class);
    private final FuentePoderRepository repository;
    
    public List<FuentePoderResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    
    public FuentePoderResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    
    public FuentePoderResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada para producto " + productoId));
    }
    public FuentePoderResponseDTO crear(FuentePoderCreateDTO dto) {
        log.info("Creando ficha tecnica");
        FuentePoder fuentePoder = new FuentePoder();
        copiarDatos(dto, fuentePoder);
        return toResponse(repository.save(fuentePoder));
    }
    public FuentePoderResponseDTO actualizar(Integer id, FuentePoderUpdateDTO dto) {
        FuentePoder fuentePoder = obtenerEntidad(id);
        copiarDatos(dto, fuentePoder);
        return toResponse(repository.save(fuentePoder));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private FuentePoder obtenerEntidad(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada con id " + id)); }
    private void copiarDatos(FuentePoderCreateDTO dto, FuentePoder fuentePoder) {
        fuentePoder.setProductoId(dto.getProductoId());
        fuentePoder.setPotenciaWatts(dto.getPotenciaWatts());
        fuentePoder.setCertificacion(dto.getCertificacion());
        fuentePoder.setModular(dto.getModular());
    }
    private void copiarDatos(FuentePoderUpdateDTO dto, FuentePoder fuentePoder) {
        fuentePoder.setProductoId(dto.getProductoId());
        fuentePoder.setPotenciaWatts(dto.getPotenciaWatts());
        fuentePoder.setCertificacion(dto.getCertificacion());
        fuentePoder.setModular(dto.getModular());
    }
    private FuentePoderResponseDTO toResponse(FuentePoder fuentePoder) {
        return FuentePoderResponseDTO.builder().id(fuentePoder.getId())
                .productoId(fuentePoder.getProductoId())
                .potenciaWatts(fuentePoder.getPotenciaWatts())
                .certificacion(fuentePoder.getCertificacion())
                .modular(fuentePoder.getModular())
                .build();
    }
}