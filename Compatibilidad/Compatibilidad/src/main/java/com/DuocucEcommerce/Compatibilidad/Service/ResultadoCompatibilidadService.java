package com.DuocucEcommerce.Compatibilidad.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DuocucEcommerce.Compatibilidad.Dto.ResultadoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ResultadoCompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ResultadoCompatibilidadUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.ResultadoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Repository.ResultadoCompatibilidadRepository;

@Service
@RequiredArgsConstructor
public class ResultadoCompatibilidadService {
    private static final Logger log = LoggerFactory.getLogger(ResultadoCompatibilidadService.class);
    private final ResultadoCompatibilidadRepository repository;

    public List<ResultadoCompatibilidadResponseDTO> listar() { return repository.findAll().stream().map(this::toResponse).toList(); }
    public ResultadoCompatibilidadResponseDTO buscarPorId(Integer id) { return toResponse(obtenerEntidad(id)); }
    public ResultadoCompatibilidadResponseDTO crear(ResultadoCompatibilidadCreateDTO dto) {
        log.info("Creando resultado compatibilidad");
        ResultadoCompatibilidad resultadoCompatibilidad = new ResultadoCompatibilidad();
        copiarDatos(dto, resultadoCompatibilidad);
        return toResponse(repository.save(resultadoCompatibilidad));
    }
    public ResultadoCompatibilidadResponseDTO actualizar(Integer id, ResultadoCompatibilidadUpdateDTO dto) {
        log.info("Actualizando resultado compatibilidad con id {}", id);
        ResultadoCompatibilidad resultadoCompatibilidad = obtenerEntidad(id);
        copiarDatos(dto, resultadoCompatibilidad);
        return toResponse(repository.save(resultadoCompatibilidad));
    }
    public void eliminar(Integer id) { repository.delete(obtenerEntidad(id)); }
    private ResultadoCompatibilidad obtenerEntidad(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ResultadoCompatibilidad no encontrado con id " + id));
    }
    private void copiarDatos(ResultadoCompatibilidadCreateDTO dto, ResultadoCompatibilidad resultadoCompatibilidad) {
        resultadoCompatibilidad.setConfiguracionId(dto.getConfiguracionId());
        resultadoCompatibilidad.setEstado(dto.getEstado());
        resultadoCompatibilidad.setMensaje(dto.getMensaje());
    }
    private void copiarDatos(ResultadoCompatibilidadUpdateDTO dto, ResultadoCompatibilidad resultadoCompatibilidad) {
        resultadoCompatibilidad.setConfiguracionId(dto.getConfiguracionId());
        resultadoCompatibilidad.setEstado(dto.getEstado());
        resultadoCompatibilidad.setMensaje(dto.getMensaje());
    }
    private ResultadoCompatibilidadResponseDTO toResponse(ResultadoCompatibilidad resultadoCompatibilidad) {
        return ResultadoCompatibilidadResponseDTO.builder()
                .id(resultadoCompatibilidad.getId())
                .configuracionId(resultadoCompatibilidad.getConfiguracionId())
                .estado(resultadoCompatibilidad.getEstado())
                .mensaje(resultadoCompatibilidad.getMensaje())
                .build();
    }
}