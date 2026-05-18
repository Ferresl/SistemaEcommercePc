package DuocucEcommerce.Producto.Service;


import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.GPUDTO.GPUCreateDTO;
import DuocucEcommerce.Producto.Dto.GPUDTO.GPUResponseDTO;
import DuocucEcommerce.Producto.Dto.GPUDTO.GPUUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.GPU;
import DuocucEcommerce.Producto.Repository.GPURepository;

@Service
@RequiredArgsConstructor
public class GPUService {
    private static final Logger log = LoggerFactory.getLogger(GPUService.class);
    
    private final GPURepository repository;
    
    public List<GPUResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    
    public GPUResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    
    public GPUResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada para producto " + productoId));
    }
    public GPUResponseDTO crear(GPUCreateDTO dto) {
        log.info("Creando ficha tecnica");
        GPU gpu = new GPU();
        copiarDatos(dto, gpu);
        return toResponse(repository.save(gpu));
    }
    public GPUResponseDTO actualizar(Integer id, GPUUpdateDTO dto) {
        GPU gpu = obtenerEntidad(id);
        copiarDatos(dto, gpu);
        return toResponse(repository.save(gpu));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private GPU obtenerEntidad(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada con id " + id)); }
    private void copiarDatos(GPUCreateDTO dto, GPU gpu) {
        gpu.setProductoId(dto.getProductoId());
        gpu.setMemoriaGb(dto.getMemoriaGb());
        gpu.setLargoMm(dto.getLargoMm());
        gpu.setTdpWatts(dto.getTdpWatts());
    }
    private void copiarDatos(GPUUpdateDTO dto, GPU gpu) {
        gpu.setProductoId(dto.getProductoId());
        gpu.setMemoriaGb(dto.getMemoriaGb());
        gpu.setLargoMm(dto.getLargoMm());
        gpu.setTdpWatts(dto.getTdpWatts());
    }
    private GPUResponseDTO toResponse(GPU gpu) {
        return GPUResponseDTO.builder().id(gpu.getId())
                .productoId(gpu.getProductoId())
                .memoriaGb(gpu.getMemoriaGb())
                .largoMm(gpu.getLargoMm())
                .tdpWatts(gpu.getTdpWatts())
                .build();
    }
}