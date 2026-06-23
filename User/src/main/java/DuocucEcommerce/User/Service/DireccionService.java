package DuocucEcommerce.User.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import DuocucEcommerce.User.Dto.DireccionDto.DireccionCreateDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionResponseDTO;
import DuocucEcommerce.User.Dto.DireccionDto.DireccionUpdateDTO;
import DuocucEcommerce.User.Exception.BadRequestException;
import DuocucEcommerce.User.Exception.ResourceNotFoundException;
import DuocucEcommerce.User.Model.Direccion;
import DuocucEcommerce.User.Repository.DireccionRepository;
import DuocucEcommerce.User.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DireccionService {
    private final DireccionRepository repository;
    private final UsuarioRepository usuarioRepository;
    
    public List<DireccionResponseDTO> listar() { 
        return repository.findAll().stream().map(this::toResponse).toList(); 
    }
    
    public DireccionResponseDTO buscarPorId(Integer id) { 
        return toResponse(obtenerEntidad(id)); 
    }
    
    public List<DireccionResponseDTO> listarPorUsuario(Integer usuarioId) { 
        return repository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList(); 
    }
    
    public DireccionResponseDTO crear(DireccionCreateDTO dto) { 
        validarUsuario(dto.getUsuarioId()); Direccion direccion = new Direccion(); 
        copiarDatos(dto, direccion); 
        return toResponse(repository.save(direccion)); 
    }
    
    public DireccionResponseDTO actualizar(Integer id, DireccionUpdateDTO dto) { 
        validarUsuario(dto.getUsuarioId()); 
        Direccion direccion = obtenerEntidad(id); 
        copiarDatos(dto, direccion); 
        return toResponse(repository.save(direccion)); 
    }
    
    public void eliminar(Integer id) { 
        repository.delete(obtenerEntidad(id)); 
    }
    
    private void validarUsuario(Integer usuarioId) { 
        if (!usuarioRepository.existsById(usuarioId)) { 
            throw new BadRequestException("Usuario no encontrado"); } 
        }
    
    private Direccion obtenerEntidad(Integer id) { 
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Direccion no encontrada con id " + id)); 
    }
    
    private void copiarDatos(DireccionCreateDTO dto, Direccion direccion) { 
        direccion.setUsuarioId(dto.getUsuarioId()); 
        direccion.setRegion(dto.getRegion()); 
        direccion.setComuna(dto.getComuna()); 
        direccion.setCalle(dto.getCalle()); 
        direccion.setNumero(dto.getNumero()); 
        direccion.setDepartamento(dto.getDepartamento()); 
        direccion.setReferencia(dto.getReferencia()); 
        direccion.setPrincipal(dto.getPrincipal()); 
    }
    
    private void copiarDatos(DireccionUpdateDTO dto, Direccion direccion) { 
        direccion.setUsuarioId(dto.getUsuarioId()); 
        direccion.setRegion(dto.getRegion()); 
        direccion.setComuna(dto.getComuna()); 
        direccion.setCalle(dto.getCalle()); 
        direccion.setNumero(dto.getNumero()); 
        direccion.setDepartamento(dto.getDepartamento()); 
        direccion.setReferencia(dto.getReferencia()); 
        direccion.setPrincipal(dto.getPrincipal()); 
    }
    
    private DireccionResponseDTO toResponse(Direccion direccion) { 
        return DireccionResponseDTO.builder()
        .id(direccion.getId())
        .usuarioId(direccion.getUsuarioId())
        .region(direccion.getRegion())
        .comuna(direccion.getComuna())
        .calle(direccion.getCalle())
        .numero(direccion.getNumero())
        .departamento(direccion.getDepartamento())
        .referencia(direccion.getReferencia())
        .principal(direccion.getPrincipal())
        .build(); }
}
