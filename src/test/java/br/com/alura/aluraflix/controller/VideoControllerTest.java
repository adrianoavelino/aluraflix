package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.VideoRequestAtualizar;
import br.com.alura.aluraflix.controller.dto.VideoRequestSalvar;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.repository.VideoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deveSalvarVideo() throws Exception {
        Categoria categoria = categoriaRepository.findById(1l).get();
        VideoRequestSalvar requestSalvar = new VideoRequestSalvar("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria.getId());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/v1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestSalvar));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void deveAtualizarVideo() throws Exception {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria);
        Video v = this.videoRepository.save(video);
        VideoRequestAtualizar videoRequest = new VideoRequestAtualizar(v.getId(), "Título Atualizado", "Descrição atualizada", "http://www.siteatualizado.com.br", categoria.getId());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/v1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoRequest));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deveBuscarVideoPorId() throws Exception {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria);
        this.videoRepository.save(video);
        String id = video.getId().toString();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/videos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deveBuscarTodosOsVideos() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/videos")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
