package br.com.alura.aluraflix.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, message = "precisa ter no mínimo {min} letras")
    @Length(max = 150, message = "precisa ter no máximo {max} letras")
    private String titulo;

    @Length(min = 3, message = "precisa ter no mínimo {min} letras")
    @Length(max = 255, message = "precisa ter no máximo {max} letras")
    private String descricao;

    @NotBlank(message = "Ops, você esqueceu da URL")
    @URL(message = "Você deve usar uma url válida. Ex: http://www.site.com.br")
    private String url;

    public Video() {
    }

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo.trim();
        this.descricao = descricao.trim();
        this.url = url.trim();
    }

    public Video(Long id, String titulo, String descricao, String url) {
        this.id = id;
        this.titulo = titulo.trim();
        this.descricao = descricao.trim();
        this.url = url.trim();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
