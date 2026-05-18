package DuocucEcommerce.Producto.Service;


import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoCreateDTO;
import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoResponseDTO;
import DuocucEcommerce.Producto.Dto.AlmacenamientoDTO.AlmacenamientoUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.Almacenamiento;
import DuocucEcommerce.Producto.Repository.AlmacenamientoRepository;

@Service
@RequiredArgsConstructor
public class AlmacenamientoService {
    private static final Logger log = LoggerFactory.getLogger(AlmacenamientoService.class);
    private final AlmacenamientoRepository repository;
    
    public List<AlmacenamientoResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    
    public AlmacenamientoResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    
    public AlmacenamientoResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada para producto " + productoId));
    }
    public AlmacenamientoResponseDTO crear(AlmacenamientoCreateDTO dto) {
        log.info("Creando ficha tecnica");
        Almacenamiento almacenamiento = new Almacenamiento();
        copiarDatos(dto, almacenamiento);
        return toResponse(repository.save(almacenamiento));
    }
    public AlmacenamientoResponseDTO actualizar(Integer id, AlmacenamientoUpdateDTO dto) {
        Almacenamiento almacenamiento = obtenerEntidad(id);
        copiarDatos(dto, almacenamiento);
        return toResponse(repository.save(almacenamiento));
    }
    
    public void eliminar(Integer id) { 
        repository.delete(obtenerEntidad(id)); 
    }
    
    private Almacenamiento obtenerEntidad(Integer id) { 
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada con id " + id)); 
    }
    
    private void copiarDatos(AlmacenamientoCreateDTO dto, Almacenamiento almacenamiento) {
        almacenamiento.setProductoId(dto.getProductoId());
        almacenamiento.setTipo(dto.getTipo());
        almacenamiento.setCapacidadGb(dto.getCapacidadGb());
        almacenamiento.setInterfaz(dto.getInterfaz());
    }
    private void copiarDatos(AlmacenamientoUpdateDTO dto, Almacenamiento almacenamiento) {
        almacenamiento.setProductoId(dto.getProductoId());
        almacenamiento.setTipo(dto.getTipo());
        almacenamiento.setCapacidadGb(dto.getCapacidadGb());
        almacenamiento.setInterfaz(dto.getInterfaz());
    }
    private AlmacenamientoResponseDTO toResponse(Almacenamiento almacenamiento) {
        return AlmacenamientoResponseDTO.builder().id(almacenamiento.getId())
                .productoId(almacenamiento.getProductoId())
                .tipo(almacenamiento.getTipo())
                .capacidadGb(almacenamiento.getCapacidadGb())
                .interfaz(almacenamiento.getInterfaz())
                .build();
    }
}