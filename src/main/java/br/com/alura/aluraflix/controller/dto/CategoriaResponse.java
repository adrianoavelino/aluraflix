package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Categoria;

public class CategoriaResponse {
    private Long id;
    private String titulo;
    private String cor;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.cor = categoria.getCor();
        this.titulo = categoria.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
