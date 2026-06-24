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

import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoCreateDTO;
import DuocucEcommerce.Pedido.Dto.DetallePedidoDTO.DetallePedidoResponseDTO;
import DuocucEcommerce.Pedido.Exception.GlobalExceptionHandler;
import DuocucEcommerce.Pedido.Service.DetallePedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class DetallePedidoControllerTest {

    @Mock
    private DetallePedidoService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private DetallePedidoResponseDTO response;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DetallePedidoController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        response = DetallePedidoResponseDTO.builder()
                .id(1).pedidoId(10).productoId(20).cantidad(2).precioUnitario(new BigDecimal("1500"))
                .build();
    }

    @Test
    void listarRetornaOk() throws Exception {
        when(service.listar()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/detalles-pedido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productoId").value(20));
    }

    @Test
    void buscarPorIdRetornaOk() throws Exception {
        when(service.buscarPorId(1)).thenReturn(response);

        mockMvc.perform(get("/api/detalles-pedido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(2));
    }

    @Test
    void crearRetornaCreated() throws Exception {
        when(service.crear(any())).thenReturn(response);
        var dto = DetallePedidoCreateDTO.builder()
                .pedidoId(10).productoId(20).cantidad(2).precioUnitario(new BigDecimal("1500"))
                .build();

        mockMvc.perform(post("/api/detalles-pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizarRetornaOk() throws Exception {
        when(service.actualizar(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/detalles-pedido/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoId").value(10));
    }

    @Test
    void eliminarRetornaNoContent() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/api/detalles-pedido/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminar(1);
    }
}