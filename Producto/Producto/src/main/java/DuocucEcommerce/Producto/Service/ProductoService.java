package DuocucEcommerce.Producto.Service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DuocucEcommerce.Producto.Client.CategoriaClient;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoCreateDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoResponseDTO;
import DuocucEcommerce.Producto.Dto.ProductoDTO.ProductoUpdateDTO;
import DuocucEcommerce.Producto.Exception.BadRequestException;
import DuocucEcommerce.Producto.Exception.ResourceNotFoundException;
import DuocucEcommerce.Producto.Model.Producto;
import DuocucEcommerce.Producto.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoRepository repository;
    private final CategoriaClient categoriaClient;

    public List<ProductoResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
   
   
    public ProductoResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
   
   
    public ProductoResponseDTO crear(ProductoCreateDTO dto) {
        log.info("Creando producto: {}", dto.getNombre());
        validarPrecio(dto.getPrecio()); categoriaClient.obtenerPorId(dto.getCategoriaId());
        Producto producto = new Producto(); copiarDatos(dto, producto); return toResponse(repository.save(producto));
    }
   
   
    public ProductoResponseDTO actualizar(Integer id, ProductoUpdateDTO dto) {
        validarPrecio(dto.getPrecio()); categoriaClient.obtenerPorId(dto.getCategoriaId());
        Producto producto = obtenerEntidad(id); copiarDatos(dto, producto); return toResponse(repository.save(producto));
    }
   
   
    public void eliminar(Integer id) { 
        repository.delete(obtenerEntidad(id)); 
    }
   
   
    public List<ProductoResponseDTO> listarPorCategoria(Integer categoriaId) { 
        return repository.findByCategoriaId(categoriaId).stream().map(this::toResponse).toList(); 
    }
   
   
    public List<ProductoResponseDTO> buscarPorTexto(String texto) { 
        return repository.findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCase(texto, texto).stream().map(this::toResponse).toList(); 
    }
   
   
    public List<ProductoResponseDTO> filtrarPorPrecio(BigDecimal rangoMin, BigDecimal rangoMax) { 
        return repository.findByPrecioBetween(rangoMin, rangoMax).stream().map(this::toResponse).toList(); 
    }
   
   
    private void validarPrecio(BigDecimal precio) { 
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) 
            { throw new BadRequestException("El precio debe ser mayor a 0"); } 
    }
   
   
    private Producto obtenerEntidad(Integer id) { 
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + id)); 
    }
   
   
    private void copiarDatos(ProductoCreateDTO dto, Producto producto) { 
        producto.setNombre(dto.getNombre());
        producto.setMarca(dto.getMarca()); 
        producto.setModelo(dto.getModelo()); 
        producto.setPrecio(dto.getPrecio()); 
        producto.setCategoriaId(dto.getCategoriaId()); 
        producto.setEstado(dto.getEstado()); 
    }
   
   
   
    private void copiarDatos(ProductoUpdateDTO dto, Producto producto) { 
        producto.setNombre(dto.getNombre()); 
        producto.setMarca(dto.getMarca()); 
        producto.setModelo(dto.getModelo()); 
        producto.setPrecio(dto.getPrecio());
         producto.setCategoriaId(dto.getCategoriaId()); 
         producto.setEstado(dto.getEstado()); 
        }

    
    private ProductoResponseDTO toResponse(Producto producto) { 
        return ProductoResponseDTO.builder()
        .id(producto.getId())
        .nombre(producto.getNombre())
        .marca(producto.getMarca())
        .modelo(producto.getModelo())
        .precio(producto.getPrecio())
        .categoriaId(producto.getCategoriaId())
        .estado(producto.getEstado())
        .build();}
    }
