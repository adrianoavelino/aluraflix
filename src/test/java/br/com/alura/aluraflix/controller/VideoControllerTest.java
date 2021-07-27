package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.entity.Video;
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
    private VideoRepository repository;

    @Test
    void deveSalvarVideo() throws Exception {
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/v1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(video));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void deveAtualizarVideo() throws Exception {
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br");
        Video videoSalvo = this.repository.save(video);
        Video videAtualizado = new Video(video.getId(), "Título Atualizado", "Descrição atualizada", "http://www.siteatualizado.com.br");;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/v1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videAtualizado));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deveBuscarVideoPorId() throws Exception {
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br");
        this.repository.save(video);
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
