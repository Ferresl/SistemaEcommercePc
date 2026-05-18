package DuocucEcommerce.Producto.Service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteCreateDTO;
import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteResponseDTO;
import DuocucEcommerce.Producto.Dto.GabineteDTO.GabineteUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.Gabinete;
import DuocucEcommerce.Producto.Repository.GabineteRepository;

@Service
@RequiredArgsConstructor
public class GabineteService {
    private static final Logger log = LoggerFactory.getLogger(GabineteService.class);
   
    private final GabineteRepository repository;
   
    public List<GabineteResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
   
    public GabineteResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
   
    public GabineteResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada para producto " + productoId));
    }
    public GabineteResponseDTO crear(GabineteCreateDTO dto) {
        log.info("Creando ficha tecnica");
        Gabinete gabinete = new Gabinete();
        copiarDatos(dto, gabinete);
        return toResponse(repository.save(gabinete));
    }
    public GabineteResponseDTO actualizar(Integer id, GabineteUpdateDTO dto) {
        Gabinete gabinete = obtenerEntidad(id);
        copiarDatos(dto, gabinete);
        return toResponse(repository.save(gabinete));
    }
    
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    
    private Gabinete obtenerEntidad(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada con id " + id)); }
    
    private void copiarDatos(GabineteCreateDTO dto, Gabinete gabinete) {
        gabinete.setProductoId(dto.getProductoId());
        gabinete.setFormatoSoportado(dto.getFormatoSoportado());
        gabinete.setLargoMaxGpuMm(dto.getLargoMaxGpuMm());
    }
    private void copiarDatos(GabineteUpdateDTO dto, Gabinete gabinete) {
        gabinete.setProductoId(dto.getProductoId());
        gabinete.setFormatoSoportado(dto.getFormatoSoportado());
        gabinete.setLargoMaxGpuMm(dto.getLargoMaxGpuMm());
    }
    private GabineteResponseDTO toResponse(Gabinete gabinete) {
        return GabineteResponseDTO.builder().id(gabinete.getId())
                .productoId(gabinete.getProductoId())
                .formatoSoportado(gabinete.getFormatoSoportado())
                .largoMaxGpuMm(gabinete.getLargoMaxGpuMm())
                .build();
    }
}