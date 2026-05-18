package com.DuocucEcommerce.ConfiguracionPc.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.DuocucEcommerce.ConfiguracionPc.Client.CompatibilidadClient;
import com.DuocucEcommerce.ConfiguracionPc.Client.CompatibilidadRequestDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.CompatibilidadResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.NotificacionClient;
import com.DuocucEcommerce.ConfiguracionPc.Client.NotificacionCreateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.ProductoClient;
import com.DuocucEcommerce.ConfiguracionPc.Client.UsuarioClient;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCCreateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCUpdateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.ConfiguracionPc.Model.ConfiguracionPC;
import com.DuocucEcommerce.ConfiguracionPc.Repository.ConfiguracionPCRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfiguracionPCService {
    private final ConfiguracionPCRepository repository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;
    private final CompatibilidadClient compatibilidadClient;
    private final NotificacionClient notificacionClient;
    public List<ConfiguracionPCResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public ConfiguracionPCResponseDTO buscarPorId(Integer id) { return toResponse(obtener(id)); }
    public List<ConfiguracionPCResponseDTO> listarPorUsuario(Integer usuarioId) { return repository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList(); }
    public ConfiguracionPCResponseDTO crear(ConfiguracionPCCreateDTO dto) { usuarioClient.validar(dto.getUsuarioId()); ConfiguracionPC c = new ConfiguracionPC(); copiar(dto, c); calcular(c); c.setEstado("BORRADOR"); return toResponse(repository.save(c)); }
    public ConfiguracionPCResponseDTO actualizar(Integer id, ConfiguracionPCUpdateDTO dto) { ConfiguracionPC c = obtener(id); copiar(dto, c); calcular(c); return toResponse(repository.save(c)); }
    public ConfiguracionPCResponseDTO evaluar(Integer id) { ConfiguracionPC c = obtener(id); calcular(c); c = repository.save(c); CompatibilidadResponseDTO r = compatibilidadClient.evaluar(CompatibilidadRequestDTO.builder().configuracionId(c.getId()).cpuId(c.getCpuId()).gpuId(c.getGpuId()).ramId(c.getRamId()).placaMadreId(c.getPlacaMadreId()).fuentePoderId(c.getFuentePoderId()).gabineteId(c.getGabineteId()).tdpTotal(c.getTdpTotal()).build()); c.setEstado("COMPATIBLE".equals(r.getEstado()) ? "COMPATIBLE" : "INCOMPATIBLE"); c = repository.save(c); if ("INCOMPATIBLE".equals(c.getEstado())) { notificacionClient.crear(NotificacionCreateDTO.builder().usuarioId(c.getUsuarioId()).titulo("Configuracion incompatible").mensaje(r.getMensaje()).tipo("CONFIGURACION").leida(false).build()); } return toResponse(c); }
    public void eliminar(Integer id) { repository.delete(obtener(id)); }
    private void calcular(ConfiguracionPC c) { BigDecimal total = BigDecimal.ZERO; Integer[] ids = {c.getCpuId(), c.getGpuId(), c.getRamId(), c.getPlacaMadreId(), c.getAlmacenamientoId(), c.getFuentePoderId(), c.getGabineteId()}; for (Integer id : ids) { total = total.add(productoClient.producto(id).getPrecio()); } Integer tdp = productoClient.cpu(c.getCpuId()).getTdpWatts() + productoClient.gpu(c.getGpuId()).getTdpWatts() + 50; c.setPrecioTotal(total); c.setTdpTotal(tdp); }
    private ConfiguracionPC obtener(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Configuracion no encontrada con id " + id)); }
    private void copiar(ConfiguracionPCCreateDTO dto, ConfiguracionPC c) { c.setUsuarioId(dto.getUsuarioId()); c.setNombre(dto.getNombre()); c.setCpuId(dto.getCpuId()); c.setGpuId(dto.getGpuId()); c.setRamId(dto.getRamId()); c.setPlacaMadreId(dto.getPlacaMadreId()); c.setAlmacenamientoId(dto.getAlmacenamientoId()); c.setFuentePoderId(dto.getFuentePoderId()); c.setGabineteId(dto.getGabineteId()); }
    private void copiar(ConfiguracionPCUpdateDTO dto, ConfiguracionPC c) { c.setUsuarioId(dto.getUsuarioId()); c.setNombre(dto.getNombre()); c.setCpuId(dto.getCpuId()); c.setGpuId(dto.getGpuId()); c.setRamId(dto.getRamId()); c.setPlacaMadreId(dto.getPlacaMadreId()); c.setAlmacenamientoId(dto.getAlmacenamientoId()); c.setFuentePoderId(dto.getFuentePoderId()); c.setGabineteId(dto.getGabineteId()); if (dto.getEstado() != null) { c.setEstado(dto.getEstado()); } }
    private ConfiguracionPCResponseDTO toResponse(ConfiguracionPC c) { return ConfiguracionPCResponseDTO.builder().id(c.getId()).usuarioId(c.getUsuarioId()).nombre(c.getNombre()).cpuId(c.getCpuId()).gpuId(c.getGpuId()).ramId(c.getRamId()).placaMadreId(c.getPlacaMadreId()).almacenamientoId(c.getAlmacenamientoId()).fuentePoderId(c.getFuentePoderId()).gabineteId(c.getGabineteId()).precioTotal(c.getPrecioTotal()).tdpTotal(c.getTdpTotal()).estado(c.getEstado()).build(); }
}
