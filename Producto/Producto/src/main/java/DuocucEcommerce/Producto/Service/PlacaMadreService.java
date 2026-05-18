package DuocucEcommerce.Producto.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreCreateDTO;
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreResponseDTO;
import DuocucEcommerce.Producto.Dto.PlacaMadreDTO.PlacaMadreUpdateDTO;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.PlacaMadre;
import DuocucEcommerce.Producto.Repository.PlacaMadreRepository;

@Service
@RequiredArgsConstructor
public class PlacaMadreService {
    private static final Logger log = LoggerFactory.getLogger(PlacaMadreService.class);
   
    private final PlacaMadreRepository repository;
   
    public List<PlacaMadreResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
   
    public PlacaMadreResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
   
    public PlacaMadreResponseDTO buscarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada para producto " + productoId));
    }
    public PlacaMadreResponseDTO crear(PlacaMadreCreateDTO dto) {
        log.info("Creando ficha tecnica");
        PlacaMadre placaMadre = new PlacaMadre();
        copiarDatos(dto, placaMadre);
        return toResponse(repository.save(placaMadre));
    }
    public PlacaMadreResponseDTO actualizar(Integer id, PlacaMadreUpdateDTO dto) {
        PlacaMadre placaMadre = obtenerEntidad(id);
        copiarDatos(dto, placaMadre);
        return toResponse(repository.save(placaMadre));
    }
   
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
   
    private PlacaMadre obtenerEntidad(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ficha tecnica no encontrada con id " + id)); }
   
   
    private void copiarDatos(PlacaMadreCreateDTO dto, PlacaMadre placaMadre) {
        placaMadre.setProductoId(dto.getProductoId());
        placaMadre.setSocket(dto.getSocket());
        placaMadre.setChipset(dto.getChipset());
        placaMadre.setTipoRamSoportada(dto.getTipoRamSoportada());
        placaMadre.setRamMaximaGb(dto.getRamMaximaGb());
        placaMadre.setFormato(dto.getFormato());
    }
    private void copiarDatos(PlacaMadreUpdateDTO dto, PlacaMadre placaMadre) {
        placaMadre.setProductoId(dto.getProductoId());
        placaMadre.setSocket(dto.getSocket());
        placaMadre.setChipset(dto.getChipset());
        placaMadre.setTipoRamSoportada(dto.getTipoRamSoportada());
        placaMadre.setRamMaximaGb(dto.getRamMaximaGb());
        placaMadre.setFormato(dto.getFormato());
    }
    private PlacaMadreResponseDTO toResponse(PlacaMadre placaMadre) {
        return PlacaMadreResponseDTO.builder().id(placaMadre.getId())
                .productoId(placaMadre.getProductoId())
                .socket(placaMadre.getSocket())
                .chipset(placaMadre.getChipset())
                .tipoRamSoportada(placaMadre.getTipoRamSoportada())
                .ramMaximaGb(placaMadre.getRamMaximaGb())
                .formato(placaMadre.getFormato())
                .build();
    }
}