package com.DuocucEcommerce.Compatibilidad.Service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Client.CPUResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.FuentePoderResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.GPUResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.GabineteResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.PlacaMadreResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.ProductoClient;
import com.DuocucEcommerce.Compatibilidad.Client.RAMResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadRequestDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.RecomendacionComponenteResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Model.ConflictoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Model.RecomendacionComponente;
import com.DuocucEcommerce.Compatibilidad.Model.ResultadoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Repository.ConflictoCompatibilidadRepository;
import com.DuocucEcommerce.Compatibilidad.Repository.RecomendacionComponenteRepository;
import com.DuocucEcommerce.Compatibilidad.Repository.ResultadoCompatibilidadRepository;

@Service
@RequiredArgsConstructor
public class CompatibilidadService {
    private final ResultadoCompatibilidadRepository resultadoRepository;
    private final ConflictoCompatibilidadRepository conflictoRepository;
    private final RecomendacionComponenteRepository recomendacionRepository;
    private final ProductoClient productoClient;

    public CompatibilidadResponseDTO evaluar(CompatibilidadRequestDTO dto) {
        CPUResponseDTO cpu = productoClient.cpu(dto.getCpuId());
        GPUResponseDTO gpu = productoClient.gpu(dto.getGpuId());
        RAMResponseDTO ram = productoClient.ram(dto.getRamId());
        PlacaMadreResponseDTO placa = productoClient.placa(dto.getPlacaMadreId());
        FuentePoderResponseDTO fuente = productoClient.fuente(dto.getFuentePoderId());
        GabineteResponseDTO gabinete = productoClient.gabinete(dto.getGabineteId());
        List<String> motivos = new ArrayList<>();
        if (!cpu.getSocket().equalsIgnoreCase(placa.getSocket())) { motivos.add("CPU y placa madre no tienen el mismo socket"); }
        if (!ram.getTipoMemoria().equalsIgnoreCase(placa.getTipoRamSoportada())) { motivos.add("La RAM no es compatible con la placa madre"); }
        if (gpu.getLargoMm() > gabinete.getLargoMaxGpuMm()) { motivos.add("La GPU no cabe en el gabinete"); }
        if (fuente.getPotenciaWatts() < dto.getTdpTotal() + 100) { motivos.add("La fuente no tiene potencia suficiente"); }
        if (!gabinete.getFormatoSoportado().toLowerCase().contains(placa.getFormato().toLowerCase())) { motivos.add("El gabinete no soporta el formato de la placa madre"); }
        String estado = motivos.isEmpty() ? "COMPATIBLE" : "INCOMPATIBLE";
        String mensaje = motivos.isEmpty() ? "La configuracion es compatible" : String.join("; ", motivos);
        ResultadoCompatibilidad resultado = resultadoRepository.save(ResultadoCompatibilidad.builder().configuracionId(dto.getConfiguracionId()).estado(estado).mensaje(mensaje).build());
        for (String motivo : motivos) {
            ConflictoCompatibilidad conflicto = conflictoRepository.save(ConflictoCompatibilidad.builder().resultadoId(resultado.getId()).productoAId(dto.getCpuId()).productoBId(dto.getPlacaMadreId()).motivo(motivo).build());
            recomendacionRepository.save(RecomendacionComponente.builder().conflictoId(conflicto.getId()).productoRecomendadoId(dto.getPlacaMadreId()).motivo("Revisar componente compatible en la misma categoria").build());
        }
        return toResponse(resultado);
    }

    public CompatibilidadResponseDTO buscarPorConfiguracion(Integer configuracionId) { return resultadoRepository.findTopByConfiguracionIdOrderByIdDesc(configuracionId).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Resultado no encontrado")); }
    
    public CompatibilidadResponseDTO buscarPorId(Integer id) { return resultadoRepository.findById(id).map(this::toResponse).orElseThrow(() -> new ResourceNotFoundException("Resultado no encontrado")); }
    
    private CompatibilidadResponseDTO toResponse(ResultadoCompatibilidad r) {
        List<ConflictoCompatibilidadResponseDTO> conflictos = conflictoRepository.findByResultadoId(r.getId()).stream().map(c -> ConflictoCompatibilidadResponseDTO.builder().id(c.getId()).resultadoId(c.getResultadoId()).productoAId(c.getProductoAId()).productoBId(c.getProductoBId()).motivo(c.getMotivo()).build()).toList();
        List<RecomendacionComponenteResponseDTO> recomendaciones = conflictos.stream().flatMap(c -> recomendacionRepository.findByConflictoId(c.getId()).stream()).map(rec -> RecomendacionComponenteResponseDTO.builder().id(rec.getId()).conflictoId(rec.getConflictoId()).productoRecomendadoId(rec.getProductoRecomendadoId()).motivo(rec.getMotivo()).build()).toList();
        return CompatibilidadResponseDTO.builder().resultadoId(r.getId()).configuracionId(r.getConfiguracionId()).estado(r.getEstado()).mensaje(r.getMensaje()).conflictos(conflictos).recomendaciones(recomendaciones).build();
    }
}