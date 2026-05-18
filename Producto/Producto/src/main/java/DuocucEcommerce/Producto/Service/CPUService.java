package DuocucEcommerce.Producto.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.CPUDTO.CPUCreateDTO;
import DuocucEcommerce.Producto.Dto.CPUDTO.CPUResponseDTO;
import DuocucEcommerce.Producto.Dto.CPUDTO.CPUUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.CPU;
import DuocucEcommerce.Producto.Repository.CPURepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CPUService {

    private static final Logger log = LoggerFactory.getLogger(CPUService.class);

    private final CPURepository repository;

    public List<CPUResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CPUResponseDTO buscarPorId(Integer id) {
        return toResponse(obtenerEntidad(id));
    }

    public CPUResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ficha tecnica no encontrada para producto " + productoId
                ));
    }

    public CPUResponseDTO crear(CPUCreateDTO dto) {
        log.info("Creando ficha tecnica CPU");

        CPU cpu = new CPU();
        copiarDatos(dto, cpu);

        return toResponse(repository.save(cpu));
    }

    public CPUResponseDTO actualizar(Integer id, CPUUpdateDTO dto) {
        CPU cpu = obtenerEntidad(id);

        copiarDatos(dto, cpu);

        return toResponse(repository.save(cpu));
    }

    public void eliminar(Integer id) {
        repository.delete(obtenerEntidad(id));
    }

    private CPU obtenerEntidad(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ficha tecnica no encontrada con id " + id
                ));
    }

    private void copiarDatos(CPUCreateDTO dto, CPU cpu) {
        cpu.setProductoId(dto.getProductoId());
        cpu.setSocket(dto.getSocket());
        cpu.setNucleos(dto.getNucleos());
        cpu.setHilos(dto.getHilos());
        cpu.setTdpWatts(dto.getTdpWatts());
    }

    private void copiarDatos(CPUUpdateDTO dto, CPU cpu) {
        cpu.setProductoId(dto.getProductoId());
        cpu.setSocket(dto.getSocket());
        cpu.setNucleos(dto.getNucleos());
        cpu.setHilos(dto.getHilos());
        cpu.setTdpWatts(dto.getTdpWatts());
    }

    private CPUResponseDTO toResponse(CPU cpu) {
        return CPUResponseDTO.builder()
                .id(cpu.getId())
                .productoId(cpu.getProductoId())
                .socket(cpu.getSocket())
                .nucleos(cpu.getNucleos())
                .hilos(cpu.getHilos())
                .tdpWatts(cpu.getTdpWatts())
                .build();
    }
}