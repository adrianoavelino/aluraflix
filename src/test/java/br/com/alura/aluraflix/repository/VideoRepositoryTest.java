package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.entity.Video;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class VideoRepositoryTest {

    @Autowired
    private VideoRepository repository;

    @Test
    void deveSalvarUmVideo() {
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br");
        long totalVideoSalvoEsperado = this.repository.count() + 1;
        Video videoSalvo = this.repository.save(video);
        Assertions.assertThat(totalVideoSalvoEsperado).isEqualTo(this.repository.count());
        Assertions.assertThat(videoSalvo).usingRecursiveComparison().ignoringFields("id").isEqualTo(video);
    }

    @Test
    void deveAtualizarVideo() {
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br");
        this.repository.save(video);

        Video videoAlterado = new Video(video.getId(), "Título Atualizado", "Descrição atualizada", "http://www.siteatualizado.com.br");
        this.repository.save(videoAlterado);

        Video videoAtual = this.repository.findById(video.getId()).get();

        Assertions.assertThat(videoAtual).usingRecursiveComparison().isEqualTo(videoAlterado);
    }

    @Test
    void deveBuscarVideoPorId() {
        Video video = new Video("TDD", "Teste Unitário", "http://www.tdd.com.br");
        this.repository.save(video);

        Video videoEncontrado = this.repository.findById(video.getId()).get();
        Assertions.assertThat(video).usingRecursiveComparison().isEqualTo(videoEncontrado);
    }

    @Test
    void deveBuscarTodosOsVideos() {
        this.repository.deleteAll();
        Video video1 = new Video("TDD 1", "Teste Unitário 1", "http://www.tdd1.com.br");
        Video video2 = new Video("TDD 2", "Teste Unitário 2", "http://www.tdd2.com.br");
        Video video3 = new Video("TDD 3", "Teste Unitário 3", "http://www.tdd3.com.br");

        Assertions.assertThat(this.repository.count()).isEqualTo(0);

        this.repository.save(video1);
        this.repository.save(video2);
        this.repository.save(video3);
        Assertions.assertThat(this.repository.count()).isEqualTo(3);
    }
}