package com.DuocucEcommerce.Notificacion.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Notificacion.Dto.NotificacionCreateDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionResponseDTO;
import com.DuocucEcommerce.Notificacion.Dto.NotificacionUpdateDTO;
import com.DuocucEcommerce.Notificacion.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Notificacion.Model.Notificacion;
import com.DuocucEcommerce.Notificacion.Repository.NotificacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository repository;
    public List<NotificacionResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public List<NotificacionResponseDTO> listarPorUsuario(Integer usuarioId) { return repository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList(); }
    public NotificacionResponseDTO buscarPorId(Integer id) { return toResponse(obtener(id)); }
    public NotificacionResponseDTO crear(NotificacionCreateDTO dto) { Notificacion n = new Notificacion(); copiar(dto, n); if (n.getLeida() == null) { n.setLeida(false); } return toResponse(repository.save(n)); }
    public NotificacionResponseDTO actualizar(Integer id, NotificacionUpdateDTO dto) { Notificacion n = obtener(id); n.setUsuarioId(dto.getUsuarioId()); n.setTitulo(dto.getTitulo()); n.setMensaje(dto.getMensaje()); n.setTipo(dto.getTipo()); n.setLeida(dto.getLeida()); return toResponse(repository.save(n)); }
    public NotificacionResponseDTO marcarLeida(Integer id) { Notificacion n = obtener(id); n.setLeida(true); return toResponse(repository.save(n)); }
    public void eliminar(Integer id) { repository.delete(obtener(id)); }
    private Notificacion obtener(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notificacion no encontrada con id " + id)); }
    private void copiar(NotificacionCreateDTO dto, Notificacion n) { n.setUsuarioId(dto.getUsuarioId()); n.setTitulo(dto.getTitulo()); n.setMensaje(dto.getMensaje()); n.setTipo(dto.getTipo()); n.setLeida(dto.getLeida()); }
    private NotificacionResponseDTO toResponse(Notificacion n) { return NotificacionResponseDTO.builder().id(n.getId()).usuarioId(n.getUsuarioId()).titulo(n.getTitulo()).mensaje(n.getMensaje()).tipo(n.getTipo()).leida(n.getLeida()).build(); }
}
