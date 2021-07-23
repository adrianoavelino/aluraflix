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

    public Video() {
    }

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
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
