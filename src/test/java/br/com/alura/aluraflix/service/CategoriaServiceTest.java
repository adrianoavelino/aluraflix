package br.com.alura.aluraflix.service;

import br.com.alura.aluraflix.controller.dto.VideoResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.repository.VideoRepository;
import br.com.alura.aluraflix.validacao.ActionNotAllowed;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class CategoriaServiceTest {

    @MockBean
    private CategoriaRepository repository;

    @MockBean
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaService service;

    @Test
    void deveBuscarTodasCategorias() {
        Categoria categoria1 = new Categoria(1l,"categoria1", "#aaaaaa");
        Categoria categoria2 = new Categoria(2l,"categoria2", "#bbbbbb");
        Categoria categoria3 = new Categoria(3l,"categoria3", "#cccccc");

        List<Categoria> categorias = Arrays.asList(categoria1, categoria2, categoria3);
        PageImpl<Categoria> categoriasPage = new PageImpl<>(categorias);

        BDDMockito.given(repository.findAll(ArgumentMatchers.any(Pageable.class)))
                .willReturn(categoriasPage);

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Categoria> categoriasEncontradas = this.service.buscarTodas(pageRequest);

        PageImpl<Categoria> categoriasPage2 = new PageImpl<>(Arrays.asList(categoria1, categoria2, new Categoria(4l,"categoria3", "#cccccc")));
        Assertions.assertThat(categoriasPage).usingRecursiveComparison().isEqualTo(categoriasEncontradas);
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoriaComId = criarCategoriaComId();
        BDDMockito.given(repository.findById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(categoriaComId));
        Optional<Categoria> categoriaEcontrada = service.buscarPorId(1l);

        Assertions.assertThat(categoriaComId).usingRecursiveComparison().isEqualTo(categoriaEcontrada.get());
    }

    @Test
    void deveSalvarUmaCategoria() {
        Categoria categoria = criarCategoria();
        Categoria categoriaComId = criarCategoriaComId();
        BDDMockito.given(repository.save(ArgumentMatchers.any(Categoria.class)))
                .willReturn(categoriaComId);

        Categoria categoriaSalva = service.salvar(categoria);

        Assertions.assertThat(categoriaSalva).usingRecursiveComparison().ignoringFields("videos").isEqualTo(categoriaComId);
    }

    @Test
    void atualizar() {
        Categoria categoriaAlterada = new Categoria(1l, "Categoria Atualizada", "#aaaccc");
        Categoria categoriaComId = criarCategoriaComId();
        BDDMockito.given(repository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(categoriaComId));
        BDDMockito.given(repository.save(ArgumentMatchers.any(Categoria.class))).willReturn(categoriaAlterada);

        Optional<Categoria> categoriaAtualizada = service.atualizar(categoriaAlterada);

        Assertions.assertThat(Optional.of(categoriaAlterada)).usingRecursiveComparison().isEqualTo(categoriaAtualizada);
    }

    @Test
    void deletar() throws ActionNotAllowed {
        Categoria categoria = new Categoria(2l,"categoria2", "#ffffff");
        BDDMockito.given(repository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(categoria));

        service.deletar(categoria.getId());

        Mockito.verify(repository, Mockito.times(1)).delete(categoria);
    }

    @Test
    void deveBuscarVideosPorCategoriaId() {
        Categoria categoria1 = new Categoria("categoria1", "#cccccc");
        Video video1 = new Video("TDD 1", "Teste Unitário 1", "http://www.tdd1.com.br", categoria1);
        Video video2 = new Video("TDD 2", "Teste Unitário 2", "http://www.tdd2.com.br", categoria1);
        List<Video> videos = Arrays.asList(video1, video2);
        Pageable primeiraPaginaComTresVideos = PageRequest.of(0, videos.size());
        PageImpl<Video> videosPage = new PageImpl<>(videos, primeiraPaginaComTresVideos, videos.size());
        BDDMockito.given(videoRepository.findAllByCategoriaId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Pageable.class))).willReturn(videosPage);

        Page<VideoResponse> videosPorCategoriaId = service.buscarVideosPorCategoriaId(1l, primeiraPaginaComTresVideos);

        Assertions.assertThat(2l).isEqualTo(videosPorCategoriaId.getTotalElements());
    }

    private Categoria criarCategoriaComId() {
        return new Categoria(1l,"categoria1", "#dddddd");
    }

    private Categoria criarCategoria() {
        return new Categoria("categoria1", "#dddddd");
    }

}
