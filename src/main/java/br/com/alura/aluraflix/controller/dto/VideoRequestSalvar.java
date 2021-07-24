package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Video;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
public class VideoRequestSalvar {
    @Length(min = 3, message = "precisa ter no mínimo {min} letras")
    @Length(max = 150, message = "precisa ter no máximo {max} letras")
    protected String titulo;

    @Length(min = 3, message = "precisa ter no mínimo {min} letras")
    @Length(max = 255, message = "precisa ter no máximo {max} letras")
    protected String descricao;

    @NotBlank(message = "Ops, você esqueceu da URL")
    @URL(message = "Você deve usar uma url válida. Ex: http://www.site.com.br")
    protected String url;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }

    public String getUrl() {
        return url.trim();
    }

    public void setUrl(String url) {
        this.url = url.trim();
    }

    public Video converterParaVideo() {
        return new Video(this.titulo, this.descricao, this.url);
    }
}
