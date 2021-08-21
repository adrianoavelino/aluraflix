package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.LoginRequest;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

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

    private HttpHeaders getHeader() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin@email.com", "123456");
        String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson);

        MvcResult result = mockMvc.perform(request)
                .andReturn();

        return getHttpHeaderAutenticado(result);
    }

    private HttpHeaders getHttpHeaderAutenticado(MvcResult result) throws UnsupportedEncodingException {
        String response = result.getResponse().getContentAsString();
        String token = JsonPath.read(response, "$.token");
        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);
        return header;
    }

    @Test
    void deveBuscarTodasCategorias() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(this.getHeader());

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
                .contentType(MediaType.APPLICATION_JSON)
                .headers(this.getHeader());

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
                .content(objectMapper.writeValueAsString(categoria))
                .headers(this.getHeader());

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void deveAtualizarUmaCategoria() throws Exception {
        Categoria categoriaModificada = new Categoria(1l, "LIVRE alterada", "#eeeccc");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/v1/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaModificada))
                .headers(this.getHeader());

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deveDeletarUmaCategoria() throws Exception {
        Categoria categoria = repository.save(new Categoria("Categoria1", "#001100"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/v1/categorias/{id}", categoria.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .headers(this.getHeader());

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deveBuscarVideosPorCategoriaId() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/categorias/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(this.getHeader());

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
