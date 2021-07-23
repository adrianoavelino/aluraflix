package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Video;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VideoRequestAtualizar {
    @NotNull(message = "Ops, vocẽ esqueceu do campo id")
    private  Long id;

    @NotBlank(message = "Ops, você esqueceu do título")
    @Length(min = 3, message = "O campo precisa ter no mínimo {min} letras")
    @Length(max = 150, message = "O campo precisa ter no máximo {max} letras")
    private String titulo;

    @NotBlank(message = "Ops, você esqueceu da descrição")
    @Length(min = 3, message = "O campo precisa ter no mínimo {min} letras")
    @Length(max = 255, message = "O campo precisa ter no máximo {max} letras")
    private String descricao;

    @NotBlank(message = "Ops, você esqueceu da URL")
    @URL(message = "Você deve usar uma url válida. Ex: http://www.site.com.br")
    private String url;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video converterParaVideo() {
        return new Video(this.id, this.titulo, this.descricao, this.url);
    }
}
