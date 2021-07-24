package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Video;

import javax.validation.constraints.NotNull;

public class VideoRequestAtualizar extends  VideoRequestSalvar {
    @NotNull(message = "Ops, vocẽ esqueceu do campo id")
    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Video converterParaVideo() {
        return new Video(this.id, this.titulo, this.descricao, this.url);
    }
}
