package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Categoria;

import javax.validation.constraints.NotNull;

public class CategoriaRequestAtualizar extends CategoriaRequestSalvar {
    @NotNull(message = "não pode estar vazio")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Categoria converterParaCategoria() {
        return new Categoria(id, this.titulo, this.cor);
    }
}
