package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.validacao.ResourceNotFound;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class VideoRequestAtualizar extends  VideoRequestSalvar {
    @NotNull(message = "Ops, vocẽ esqueceu do campo id")
    private  Long id;

    public VideoRequestAtualizar() {
    }

    public VideoRequestAtualizar(Long id, String titulo, String descricao, String url, Long categoriaId) {
        super(titulo, descricao, url, categoriaId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Video converterParaVideo(CategoriaRepository repository) throws ResourceNotFound {
        Optional<Categoria> categoriaOptional = repository.findById(this.categoriaId);
        if (categoriaOptional.isPresent()) {
            return new Video(this.id, this.titulo, this.descricao, this.url, categoriaOptional.get());
        }
        throw new ResourceNotFound("Categoria não encontrada");
    }
}
