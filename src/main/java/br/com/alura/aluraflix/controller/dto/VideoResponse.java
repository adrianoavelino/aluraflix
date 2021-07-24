package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Video;

public class VideoResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public VideoResponse(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }
}
