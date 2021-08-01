package br.com.alura.aluraflix.service;

import br.com.alura.aluraflix.controller.dto.VideoResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.repository.VideoRepository;
import br.com.alura.aluraflix.validacao.ActionNotAllowed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final VideoRepository videoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    public Page<Categoria> buscarTodas(Pageable pageable) {
        return this.categoriaRepository.findAll(pageable);
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return this.categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Optional<Categoria> atualizar(Categoria categoria) {
        Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(categoria.getId());
        if (categoriaOptional.isPresent()) {
            this.categoriaRepository.save(categoria);
            return Optional.of(categoria);
        }
        return Optional.empty();
    }

    public Optional<Categoria> deletar(Long id) throws ActionNotAllowed {
        if (id == 1L) throw new ActionNotAllowed("Não é permitido deletar a categoria padrão LIVRE");

        Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            this.categoriaRepository.delete(categoriaOptional.get());
            return categoriaOptional;
        }
        return Optional.empty();
    }

    public Page<VideoResponse> buscarVideosPorCategoriaId(Long categoriaId, Pageable pageable) {
        List<VideoResponse> videosPorCategoriaId = this.videoRepository
                .findAllByCategoriaId(categoriaId, pageable)
                .stream()
                .map(VideoResponse::new)
                .collect(Collectors.toList());
        return new PageImpl<>(videosPorCategoriaId);
    }
}
