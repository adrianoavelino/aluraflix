package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Video;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class VideoRequestSalvar {
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
        return new Video(this.titulo, this.descricao, this.url);
    }
}
