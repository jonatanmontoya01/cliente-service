package com.denkitronik.clienteservice.delivery.rest.controllers;

import com.denkitronik.clienteservice.domain.entities.Cliente;
import com.denkitronik.clienteservice.domain.entities.Region;
import com.denkitronik.clienteservice.domain.exception.ClienteNotFoundException;
import java.util.List;
import com.denkitronik.clienteservice.delivery.exception.GlobalExceptionHandler;
import com.denkitronik.clienteservice.delivery.rest.controllers.ClienteRestController;
import com.denkitronik.clienteservice.domain.services.IClienteService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(ClienteRestController.class)
@Import({ClienteRestController.class, GlobalExceptionHandler.class})
@TestPropertySource(properties = "api.version=v1")
@DisplayName("Integration tests (web slice) — ClienteRestController con MockMvc")
class ClienteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;
    private static final String BASE = "/api/v1/cliente-service";

    @BeforeEach
    void setUp() {
        Region region = new Region();
        region.setId(4L);
        region.setNombre("Europa");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Ada");
        cliente.setApellido("Lovelace");
        cliente.setEmail("ada@babbage.uk");
        cliente.setRegion(region);
    }

    @Test
    @DisplayName("GET /clientes/1 → 200 OK con el cliente")
    void buscarCliente_idExistente_retorna200() throws Exception {
        when(clienteService.findById(1L)).thenReturn(cliente);

        mockMvc.perform(get(BASE + "/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ada"))
                .andExpect(jsonPath("$.apellido").value("Lovelace"));
    }

    @Test
    @DisplayName("GET /clientes/999 → 404 Not Found")
    void buscarCliente_idInexistente_retorna404() throws Exception {
        when(clienteService.findById(999L)).thenThrow(new ClienteNotFoundException(999L));

        mockMvc.perform(get(BASE + "/clientes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /clientes válido → 201 Created")
    void crearCliente_valido_retorna201() throws Exception {
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post(BASE + "/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Ada"));
    }

    @Test
    @DisplayName("POST /clientes con nombre vacío → 400 Bad Request")
    void crearCliente_nombreVacio_retorna400() throws Exception {
        cliente.setNombre("");

        mockMvc.perform(post(BASE + "/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /clientes/1 válido → 201 Created")
    void actualizarCliente_valido_retorna201() throws Exception {
        when(clienteService.update(eq(1L), any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put(BASE + "/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE /clientes/1 → 204 No Content")
    void eliminarCliente_existente_retorna204() throws Exception {
        doNothing().when(clienteService).delete(1L);

        mockMvc.perform(delete(BASE + "/clientes/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("GET /clientes → 200 con la lista de clientes")
    void listarClientes_debeRetornar200ConLista() throws Exception {
        when(clienteService.findAll()).thenReturn(List.of(cliente));

        mockMvc.perform(get(BASE + "/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ada"));
    }

    @Test
    @DisplayName("GET /regiones → 200 con la lista de regiones")
    void listarRegiones_debeRetornar200() throws Exception {
        when(clienteService.findAllRegiones()).thenReturn(List.of(cliente.getRegion()));

        mockMvc.perform(get(BASE + "/regiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Europa"));
    }

    @Test
    @DisplayName("POST /clientes con email inválido → 400")
    void crearCliente_emailInvalido_retorna400() throws Exception {
        cliente.setEmail("esto-no-es-un-email");

        mockMvc.perform(post(BASE + "/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /clientes/999 → 404 cuando el cliente no existe")
    void actualizarCliente_idInexistente_retorna404() throws Exception {
        when(clienteService.update(eq(999L), any(Cliente.class)))
                .thenThrow(new ClienteNotFoundException(999L));

        mockMvc.perform(put(BASE + "/clientes/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /clientes/999 → 404 cuando el cliente no existe")
    void eliminarCliente_idInexistente_retorna404() throws Exception {
        doThrow(new ClienteNotFoundException(999L)).when(clienteService).delete(999L);

        mockMvc.perform(delete(BASE + "/clientes/999"))
                .andExpect(status().isNotFound());
    }
}