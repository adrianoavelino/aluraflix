package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveBuscarTodasCategorias() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void buscarPorId() throws Exception {
        Categoria categoria = repository.findById(1l).get();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/categorias/{id}", categoria.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deveSalvarUmaCategoria() throws Exception {
        Categoria categoria = new Categoria("categoria4", "#dddddd");
        repository.save(categoria);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

}
