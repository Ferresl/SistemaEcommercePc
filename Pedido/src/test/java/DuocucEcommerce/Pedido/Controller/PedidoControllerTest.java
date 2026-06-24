package DuocucEcommerce.Pedido.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoRequestDTO;
import DuocucEcommerce.Pedido.Dto.EstadoPedidoDTO.EstadoPedidoUpdateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.PedidoDTO.PedidoResponseDTO;
import DuocucEcommerce.Pedido.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Pedido.Service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PedidoResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = PedidoResponseDTO.builder()
                .id(1).usuarioId(2).direccionId(3)
                .subtotal(new BigDecimal("3000")).total(new BigDecimal("3000"))
                .estado("CREADO").codigoConfirmacion("PED-ABC12345").detalles(List.of())
                .build();
    }

    @Test
    void listarRetornaOk() throws Exception {
        when(service.listar()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(2));
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigoConfirmacion").value("PED-ABC12345"));
    }

    @Test
    void listarPorUsuarioRetornaOk() throws Exception {
        when(service.listarPorUsuario(2)).thenReturn(List.of(response));

        mockMvc.perform(get("/api/pedidos/usuario/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("CREADO"));
    }

    @Test
    void crearRetornaCreated() throws Exception {
        var dto = PedidoCreateDTO.builder()
                .usuarioId(2).direccionId(3).detalles(List.of(new DetallePedidoRequestDTO(20, 2)))
                .build();
        when(service.crear(any())).thenReturn(response);

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizarRetornaOk() throws Exception {
        when(service.actualizar(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/pedidos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(3000));
    }

    @Test
    void cambiarEstadoRetornaOk() throws Exception {
        when(service.cambiarEstado(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/pedidos/1/estado")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EstadoPedidoUpdateDTO("ENVIADO"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CREADO"));
    }

    @Test
    void eliminarRetornaNoContent() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/api/pedidos/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminar(1);
    }
}