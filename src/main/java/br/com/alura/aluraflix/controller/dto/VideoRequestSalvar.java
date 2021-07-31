package br.com.alura.aluraflix.controller.dto;

import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.validacao.ResourceNotFound;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

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

    protected Long categoriaId;

    public VideoRequestSalvar() {
    }

    public VideoRequestSalvar(String titulo, String descricao, String url, Long categoriaId) {
        this.titulo = titulo.trim();
        this.descricao = descricao.trim();
        this.url = url.trim();
        this.categoriaId = categoriaId;
    }


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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video converterParaVideo(CategoriaRepository repository) throws ResourceNotFound {
        Optional<Categoria> categoriaOptional = repository.findById(this.categoriaId);
        if (categoriaOptional.isPresent()) {
            return new Video(this.titulo, this.descricao, this.url, categoriaOptional.get());
        }
        throw new ResourceNotFound("Categoria não encontrada");
    }
}
