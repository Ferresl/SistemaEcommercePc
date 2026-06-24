package com.DuocucEcommerce.Categoria.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.DuocucEcommerce.Categoria.Dto.CategoriaCreateDTO;
import com.DuocucEcommerce.Categoria.Dto.CategoriaResponseDTO;
import com.DuocucEcommerce.Categoria.Dto.CategoriaUpdateDTO;
import com.DuocucEcommerce.Categoria.Exception.GlobalExceptionHandler;
import com.DuocucEcommerce.Categoria.Exception.ResourceNotFoundException;
import com.DuocucEcommerce.Categoria.Model.TipoProducto;
import com.DuocucEcommerce.Categoria.Service.CategoriaService;

@ExtendWith(MockitoExtension.class) // sin Spring completo, solo controller con mocks
public class CategoriaControllerTest {

    private MockMvc mockMvc; // simula las peticiones HTTP

    @Mock
    private CategoriaService service; // servicio falso

    @InjectMocks
    private CategoriaController controller;

    private CategoriaResponseDTO categoriaEjemplo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        categoriaEjemplo = CategoriaResponseDTO.builder()
                .id(1)
                .nombre("Procesadores")
                .tipoProducto(TipoProducto.CPU)
                .build();
    }

    @Test
    void listar_retorna200ConCategorias() throws Exception {
        // ARRANGE
        List<CategoriaResponseDTO> listaFalsa = new ArrayList<>();
        listaFalsa.add(categoriaEjemplo);
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT
        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Procesadores"));
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(categoriaEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE
        when(service.buscarPorId(99)).thenThrow(new ResourceNotFoundException("Categoria no encontrado con id 99"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/categorias/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_retorna201() throws Exception {
        // ARRANGE
        when(service.crear(any(CategoriaCreateDTO.class))).thenReturn(categoriaEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Procesadores\",\"tipoProducto\":\"CPU\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Procesadores"));
    }

    @Test
    void actualizar_retorna200() throws Exception {
        // ARRANGE
        when(service.actualizar(eq(1), any(CategoriaUpdateDTO.class))).thenReturn(categoriaEjemplo);

        // ACT + ASSERT
        mockMvc.perform(put("/api/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Procesadores\",\"tipoProducto\":\"CPU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoProducto").value("CPU"));
    }

    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1);
    }
}