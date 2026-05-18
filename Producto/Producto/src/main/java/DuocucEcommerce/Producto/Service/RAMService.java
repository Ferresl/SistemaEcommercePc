package DuocucEcommerce.Producto.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.RAMDTO.RAMCreateDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMResponseDTO;
import DuocucEcommerce.Producto.Dto.RAMDTO.RAMUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.RAM;
import DuocucEcommerce.Producto.Repository.RAMRepository;

@Service
@RequiredArgsConstructor
public class RAMService {
    private static final Logger log = LoggerFactory.getLogger(RAMService.class);
    private final RAMRepository repository;
    
    
    public List<RAMResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    
    
    public RAMResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    
    
    public RAMResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada para producto " + productoId));
    }
    
    
    public RAMResponseDTO crear(RAMCreateDTO dto) {
        log.info("Creando ficha tecnica");
        RAM ram = new RAM();
        copiarDatos(dto, ram);
        return toResponse(repository.save(ram));
    }
    
    
    public RAMResponseDTO actualizar(Integer id, RAMUpdateDTO dto) {
        RAM ram = obtenerEntidad(id);
        copiarDatos(dto, ram);
        return toResponse(repository.save(ram));
    }
    
    
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    
    
    private RAM obtenerEntidad(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada con id " + id)); }
    
    
    private void copiarDatos(RAMCreateDTO dto, RAM ram) {
        ram.setProductoId(dto.getProductoId());
        ram.setTipoMemoria(dto.getTipoMemoria());
        ram.setCapacidadGb(dto.getCapacidadGb());
        ram.setFrecuenciaMhz(dto.getFrecuenciaMhz());
    }
    
    
    private void copiarDatos(RAMUpdateDTO dto, RAM ram) {
        ram.setProductoId(dto.getProductoId());
        ram.setTipoMemoria(dto.getTipoMemoria());
        ram.setCapacidadGb(dto.getCapacidadGb());
        ram.setFrecuenciaMhz(dto.getFrecuenciaMhz());
    }
    
    
    private RAMResponseDTO toResponse(RAM ram) {
        return RAMResponseDTO.builder().id(ram.getId())
                .productoId(ram.getProductoId())
                .tipoMemoria(ram.getTipoMemoria())
                .capacidadGb(ram.getCapacidadGb())
                .frecuenciaMhz(ram.getFrecuenciaMhz())
                .build();
    }
}