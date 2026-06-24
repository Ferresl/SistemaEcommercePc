package com.DuocucEcommerce.Compatibilidad.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.Compatibilidad.Client.CPUResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.FuentePoderResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.GPUResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.GabineteResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.PlacaMadreResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Client.ProductoClient;
import com.DuocucEcommerce.Compatibilidad.Client.RAMResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadRequestDTO;
import com.DuocucEcommerce.Compatibilidad.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Compatibilidad.Model.ConflictoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Model.RecomendacionComponente;
import com.DuocucEcommerce.Compatibilidad.Model.ResultadoCompatibilidad;
import com.DuocucEcommerce.Compatibilidad.Repository.ConflictoCompatibilidadRepository;
import com.DuocucEcommerce.Compatibilidad.Repository.RecomendacionComponenteRepository;
import com.DuocucEcommerce.Compatibilidad.Repository.ResultadoCompatibilidadRepository;

@ExtendWith(MockitoExtension.class)
class CompatibilidadServiceTest {

    @Mock
    private ResultadoCompatibilidadRepository resultadoRepository;

    @Mock
    private ConflictoCompatibilidadRepository conflictoRepository;

    @Mock
    private RecomendacionComponenteRepository recomendacionRepository;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private CompatibilidadService service;

    private CompatibilidadRequestDTO request;
    private ResultadoCompatibilidad resultadoCompatible;

    @BeforeEach
    void setUp() {
        request = CompatibilidadRequestDTO.builder()
                .configuracionId(1).cpuId(10).gpuId(20).ramId(30).placaMadreId(40)
                .fuentePoderId(50).gabineteId(60).tdpTotal(450)
                .build();
        resultadoCompatible = ResultadoCompatibilidad.builder()
                .id(1).configuracionId(1).estado("COMPATIBLE").mensaje("La configuracion es compatible")
                .build();
    }

    @Test
    void evaluarCompatibleGuardaResultadoSinConflictos() {
        mockComponentesCompatibles();
        when(resultadoRepository.save(any(ResultadoCompatibilidad.class))).thenReturn(resultadoCompatible);
        when(conflictoRepository.findByResultadoId(1)).thenReturn(List.of());

        var respuesta = service.evaluar(request);

        assertEquals("COMPATIBLE", respuesta.getEstado());
        assertEquals(0, respuesta.getConflictos().size());
        verify(resultadoRepository).save(any(ResultadoCompatibilidad.class));
    }

    @Test
    void evaluarIncompatibleGuardaConflictoYRecomendacion() {
        mockComponentesCompatibles();
        var cpu = new CPUResponseDTO();
        cpu.setProductoId(10);
        cpu.setSocket("AM5");
        cpu.setTdpWatts(65);
        when(productoClient.cpu(10)).thenReturn(cpu);
        var resultado = ResultadoCompatibilidad.builder()
                .id(2).configuracionId(1).estado("INCOMPATIBLE")
                .mensaje("CPU y placa madre no tienen el mismo socket")
                .build();
        var conflicto = ConflictoCompatibilidad.builder()
                .id(5).resultadoId(2).productoAId(10).productoBId(40)
                .motivo("CPU y placa madre no tienen el mismo socket")
                .build();
        var recomendacion = RecomendacionComponente.builder()
                .id(6).conflictoId(5).productoRecomendadoId(40)
                .motivo("Revisar componente compatible en la misma categoria")
                .build();
        when(resultadoRepository.save(any(ResultadoCompatibilidad.class))).thenReturn(resultado);
        when(conflictoRepository.save(any(ConflictoCompatibilidad.class))).thenReturn(conflicto);
        when(recomendacionRepository.save(any(RecomendacionComponente.class))).thenReturn(recomendacion);
        when(conflictoRepository.findByResultadoId(2)).thenReturn(List.of(conflicto));
        when(recomendacionRepository.findByConflictoId(5)).thenReturn(List.of(recomendacion));

        var respuesta = service.evaluar(request);

        assertEquals("INCOMPATIBLE", respuesta.getEstado());
        assertEquals(1, respuesta.getConflictos().size());
        assertEquals(1, respuesta.getRecomendaciones().size());
        verify(conflictoRepository).save(any(ConflictoCompatibilidad.class));
        verify(recomendacionRepository).save(any(RecomendacionComponente.class));
    }

    @Test
    void buscarPorConfiguracionRetornaUltimoResultado() {
        when(resultadoRepository.findTopByConfiguracionIdOrderByIdDesc(1)).thenReturn(Optional.of(resultadoCompatible));
        when(conflictoRepository.findByResultadoId(1)).thenReturn(List.of());

        var respuesta = service.buscarPorConfiguracion(1);

        assertEquals(1, respuesta.getConfiguracionId());
        assertEquals("COMPATIBLE", respuesta.getEstado());
    }

    @Test
    void buscarPorConfiguracionInexistenteLanzaExcepcion() {
        when(resultadoRepository.findTopByConfiguracionIdOrderByIdDesc(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorConfiguracion(99));
    }

    @Test
    void buscarPorIdRetornaResultado() {
        when(resultadoRepository.findById(1)).thenReturn(Optional.of(resultadoCompatible));
        when(conflictoRepository.findByResultadoId(1)).thenReturn(List.of());

        var respuesta = service.buscarPorId(1);

        assertEquals("La configuracion es compatible", respuesta.getMensaje());
    }

    private void mockComponentesCompatibles() {
        var cpu = new CPUResponseDTO();
        cpu.setProductoId(10);
        cpu.setSocket("AM4");
        cpu.setTdpWatts(65);
        var gpu = new GPUResponseDTO();
        gpu.setProductoId(20);
        gpu.setLargoMm(250);
        gpu.setTdpWatts(180);
        var ram = new RAMResponseDTO();
        ram.setProductoId(30);
        ram.setTipoMemoria("DDR4");
        var placa = new PlacaMadreResponseDTO();
        placa.setProductoId(40);
        placa.setSocket("AM4");
        placa.setTipoRamSoportada("DDR4");
        placa.setFormato("ATX");
        var fuente = new FuentePoderResponseDTO();
        fuente.setProductoId(50);
        fuente.setPotenciaWatts(650);
        var gabinete = new GabineteResponseDTO();
        gabinete.setProductoId(60);
        gabinete.setFormatoSoportado("ATX, Micro ATX");
        gabinete.setLargoMaxGpuMm(320);

        when(productoClient.cpu(10)).thenReturn(cpu);
        when(productoClient.gpu(20)).thenReturn(gpu);
        when(productoClient.ram(30)).thenReturn(ram);
        when(productoClient.placa(40)).thenReturn(placa);
        when(productoClient.fuente(50)).thenReturn(fuente);
        when(productoClient.gabinete(60)).thenReturn(gabinete);
    }
}