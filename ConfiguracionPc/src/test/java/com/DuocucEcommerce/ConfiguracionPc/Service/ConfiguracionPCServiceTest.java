package com.DuocucEcommerce.ConfiguracionPc.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DuocucEcommerce.ConfiguracionPc.Client.CPUResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.CompatibilidadClient;
import com.DuocucEcommerce.ConfiguracionPc.Client.CompatibilidadResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.GPUResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.NotificacionClient;
import com.DuocucEcommerce.ConfiguracionPc.Client.ProductoClient;
import com.DuocucEcommerce.ConfiguracionPc.Client.ProductoResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Client.UsuarioClient;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCCreateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCUpdateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.ConfiguracionPc.Model.ConfiguracionPC;
import com.DuocucEcommerce.ConfiguracionPc.Repository.ConfiguracionPCRepository;

@ExtendWith(MockitoExtension.class)
class ConfiguracionPCServiceTest {

    @Mock
    private ConfiguracionPCRepository repository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private ProductoClient productoClient;

    @Mock
    private CompatibilidadClient compatibilidadClient;

    @Mock
    private NotificacionClient notificacionClient;

    @InjectMocks
    private ConfiguracionPCService service;

    private ConfiguracionPC configuracion;

    @BeforeEach
    void setUp() {
        configuracion = ConfiguracionPC.builder()
                .id(1).usuarioId(2).nombre("PC Gamer")
                .cpuId(10).gpuId(20).ramId(30).placaMadreId(40).almacenamientoId(50)
                .fuentePoderId(60).gabineteId(70)
                .precioTotal(new BigDecimal("7000")).tdpTotal(300).estado("BORRADOR")
                .build();
    }

    @Test
    void listarRetornaConfiguraciones() {
        when(repository.findAll()).thenReturn(List.of(configuracion));

        var respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals("PC Gamer", respuesta.get(0).getNombre());
    }

    @Test
    void buscarPorIdRetornaConfiguracion() {
        when(repository.findById(1)).thenReturn(Optional.of(configuracion));

        var respuesta = service.buscarPorId(1);

        assertEquals(2, respuesta.getUsuarioId());
        assertEquals(new BigDecimal("7000"), respuesta.getPrecioTotal());
    }

    @Test
    void buscarPorIdInexistenteLanzaExcepcion() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));
    }

    @Test
    void listarPorUsuarioRetornaConfiguraciones() {
        when(repository.findByUsuarioId(2)).thenReturn(List.of(configuracion));

        var respuesta = service.listarPorUsuario(2);

        assertEquals(1, respuesta.size());
        verify(repository).findByUsuarioId(2);
    }

    @Test
    void crearValidaUsuarioCalculaPrecioYTdp() {
        var dto = crearDto();
        mockProductos();
        when(repository.save(any(ConfiguracionPC.class))).thenAnswer(invocation -> {
            ConfiguracionPC guardada = invocation.getArgument(0);
            guardada.setId(1);
            return guardada;
        });

        var respuesta = service.crear(dto);

        assertEquals("BORRADOR", respuesta.getEstado());
        assertEquals(new BigDecimal("7000"), respuesta.getPrecioTotal());
        assertEquals(300, respuesta.getTdpTotal());
        verify(usuarioClient).validar(2);
        verify(repository).save(any(ConfiguracionPC.class));
    }

    @Test
    void actualizarModificaYRecalcula() {
        var dto = ConfiguracionPCUpdateDTO.builder()
                .usuarioId(2).nombre("PC Editada")
                .cpuId(10).gpuId(20).ramId(30).placaMadreId(40).almacenamientoId(50)
                .fuentePoderId(60).gabineteId(70).estado("COMPATIBLE")
                .build();
        mockProductos();
        when(repository.findById(1)).thenReturn(Optional.of(configuracion));
        when(repository.save(any(ConfiguracionPC.class))).thenReturn(configuracion);

        var respuesta = service.actualizar(1, dto);

        assertEquals("PC Editada", respuesta.getNombre());
        assertEquals("COMPATIBLE", respuesta.getEstado());
    }

    @Test
    void evaluarCompatibleActualizaEstadoSinNotificar() {
        mockProductos();
        var compatibilidad = new CompatibilidadResponseDTO();
        compatibilidad.setResultadoId(1);
        compatibilidad.setConfiguracionId(1);
        compatibilidad.setEstado("COMPATIBLE");
        compatibilidad.setMensaje("Ok");
        when(repository.findById(1)).thenReturn(Optional.of(configuracion));
        when(repository.save(any(ConfiguracionPC.class))).thenReturn(configuracion);
        when(compatibilidadClient.evaluar(any())).thenReturn(compatibilidad);

        var respuesta = service.evaluar(1);

        assertEquals("COMPATIBLE", respuesta.getEstado());
        verify(compatibilidadClient).evaluar(any());
    }

    @Test
    void evaluarIncompatibleNotificaUsuario() {
        mockProductos();
        var compatibilidad = new CompatibilidadResponseDTO();
        compatibilidad.setResultadoId(1);
        compatibilidad.setConfiguracionId(1);
        compatibilidad.setEstado("INCOMPATIBLE");
        compatibilidad.setMensaje("No compatible");
        when(repository.findById(1)).thenReturn(Optional.of(configuracion));
        when(repository.save(any(ConfiguracionPC.class))).thenReturn(configuracion);
        when(compatibilidadClient.evaluar(any())).thenReturn(compatibilidad);

        var respuesta = service.evaluar(1);

        assertEquals("INCOMPATIBLE", respuesta.getEstado());
        verify(notificacionClient).crear(any());
    }

    @Test
    void eliminarBorraConfiguracion() {
        when(repository.findById(1)).thenReturn(Optional.of(configuracion));

        service.eliminar(1);

        verify(repository).delete(configuracion);
    }

    private ConfiguracionPCCreateDTO crearDto() {
        return ConfiguracionPCCreateDTO.builder()
                .usuarioId(2).nombre("PC Gamer")
                .cpuId(10).gpuId(20).ramId(30).placaMadreId(40).almacenamientoId(50)
                .fuentePoderId(60).gabineteId(70)
                .build();
    }

    private void mockProductos() {
        for (int id : List.of(10, 20, 30, 40, 50, 60, 70)) {
            var producto = new ProductoResponseDTO();
            producto.setId(id);
            producto.setPrecio(new BigDecimal("1000"));
            when(productoClient.producto(id)).thenReturn(producto);
        }
        var cpu = new CPUResponseDTO();
        cpu.setProductoId(10);
        cpu.setTdpWatts(100);
        var gpu = new GPUResponseDTO();
        gpu.setProductoId(20);
        gpu.setTdpWatts(150);
        when(productoClient.cpu(10)).thenReturn(cpu);
        when(productoClient.gpu(20)).thenReturn(gpu);
    }
}