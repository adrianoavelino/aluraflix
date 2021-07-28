package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequestSalvar {
    @NotBlank(message = "Ops, você esqueceu do título")
    protected String titulo;
    @NotBlank(message = "Ops, você esqueceu da cor")
    protected String cor;

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

    public Categoria converterParaCategoria() {
        return new Categoria(this.titulo, this.cor);
    }
}
