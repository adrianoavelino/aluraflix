package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

@SpringBootTest
@ActiveProfiles("test")
class VideoRepositoryTest {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deveSalvarUmVideo() {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria);
        long totalVideoSalvoEsperado = this.videoRepository.count() + 1;
        Video videoSalvo = this.videoRepository.save(video);
        Assertions.assertThat(totalVideoSalvoEsperado).isEqualTo(this.videoRepository.count());
        Assertions.assertThat(videoSalvo).usingRecursiveComparison().ignoringFields("id").isEqualTo(video);
    }

    @Test
    void deveMostrarErrosWhenSalvarVideoComDadosInvalidos() {
        Video video = new Video("", "", "", new Categoria());

        Throwable throable = org.junit.jupiter.api.Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> this.videoRepository.save(video)
        );

        Assertions.assertThat(throable).isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThat(throable.getMessage()).contains("precisa ter no mínimo 3 letras");
        Assertions.assertThat(throable.getMessage()).contains("Ops, você esqueceu da URL");
    }

    @Test
    void deveMostrarErroQuandoSalvarVideoComUrlInvalida() {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "urlinvalida.com.br", categoria);

        Throwable throwable = org.junit.jupiter.api.Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> this.videoRepository.save(video)
        );

        Assertions.assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThat(throwable.getMessage()).contains("Você deve usar uma url válida. Ex: http://www.site.com.br");
    }

    @Test
    void deveAtualizarVideo() {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria);
        Video save = this.videoRepository.save(video);

        Video videoAlterado = new Video(video.getId(), "Título Atualizado", "Descrição atualizada", "http://www.siteatualizado.com.br", categoria);
        this.videoRepository.save(videoAlterado);

        Video videoAtual = this.videoRepository.findById(save.getId()).get();

        Assertions.assertThat(videoAtual).usingRecursiveComparison().ignoringFields("categoria.videos").isEqualTo(videoAlterado);
    }

    @Test
    void deveMostrarErrosWhenAtualizarVideoComDadosInvalidos() {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria);
        this.videoRepository.save(video);
        Video videoAlterado = new Video(video.getId(), "", "", "", categoria);

        Throwable throwable = org.junit.jupiter.api.Assertions.assertThrows(
            ConstraintViolationException.class,
            () -> {
                try {
                    this.videoRepository.save(videoAlterado);
                } catch (Throwable e) {
                    throw e.getCause().getCause();
                }
            }
        );

        Assertions.assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThat(throwable.getMessage()).contains("precisa ter no mínimo 3 letras");
        Assertions.assertThat(throwable.getMessage()).contains("Ops, você esqueceu da URL");
    }

    @Test
    void deveMostrarErroQuandoAtualizarVideoComUrlInvalida() {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br", categoria);
        Video videoAlterado = new Video(video.getId(), "TDD Atualizado", "Teste Unitário", "urlinvalida", categoria);

        Throwable throwable = org.junit.jupiter.api.Assertions.assertThrows(
            ConstraintViolationException.class,
            () -> this.videoRepository.save(videoAlterado)
        );

        Assertions.assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThat(throwable.getMessage()).contains("Você deve usar uma url válida. Ex: http://www.site.com.br");
    }

    @Test
    void deveBuscarVideoPorId() {
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br",categoria);
        this.videoRepository.save(video);

        Video videoEncontrado = this.videoRepository.findById(video.getId()).get();
        Assertions.assertThat(video).usingRecursiveComparison().ignoringFields("categoria.videos").isEqualTo(videoEncontrado);
    }

    @Test
    void deveBuscarTodosOsVideos() {
        this.videoRepository.deleteAll();
        Categoria categoria = categoriaRepository.findById(1l).get();
        Video video1 = new Video("TDD 1", "Teste Unitário 1", "http://www.tdd1.com.br", categoria);
        Video video2 = new Video("TDD 2", "Teste Unitário 2", "http://www.tdd2.com.br", categoria);
        Video video3 = new Video("TDD 3", "Teste Unitário 3", "http://www.tdd3.com.br", categoria);

        Assertions.assertThat(this.videoRepository.count()).isEqualTo(0);

        this.videoRepository.save(video1);
        this.videoRepository.save(video2);
        this.videoRepository.save(video3);
        Assertions.assertThat(this.videoRepository.count()).isEqualTo(3);
    }
}