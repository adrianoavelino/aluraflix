package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.CategoriaRequestAtualizar;
import br.com.alura.aluraflix.controller.dto.CategoriaRequestSalvar;
import br.com.alura.aluraflix.controller.dto.CategoriaResponse;
import br.com.alura.aluraflix.controller.dto.VideoResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.repository.VideoRepository;
import br.com.alura.aluraflix.validacao.ActionNotAllowed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final CategoriaRepository categoriaRepository;
    private final VideoRepository videoRepository;

    public CategoriaController(CategoriaRepository repository, VideoRepository videoRepository) {
        this.categoriaRepository = repository;
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public Page<CategoriaResponse> buscarTodas(Pageable pageable) {
        return this.categoriaRepository.findAll(pageable).map(CategoriaResponse::new);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) return ResponseEntity.ok(new CategoriaResponse(categoriaOptional.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvar(@RequestBody @Valid CategoriaRequestSalvar categoriaRequest, UriComponentsBuilder uriBuilder) {
        Categoria categoria = this.categoriaRepository.save(categoriaRequest.converterParaCategoria());
        URI uri = uriBuilder.path("/v1/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaResponse(categoria));
    }

    @PutMapping
    public ResponseEntity<CategoriaResponse> atualizar(@RequestBody @Valid CategoriaRequestAtualizar categoriaRequest) {
        Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(categoriaRequest.getId());
        if (categoriaOptional.isPresent()) {
            this.categoriaRepository.save(categoriaRequest.converterParaCategoria());
            return ResponseEntity.ok(new CategoriaResponse(categoriaRequest.converterParaCategoria()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) throws ActionNotAllowed {
        if (id == 1L) throw new ActionNotAllowed("Não é permitido deletar a categoria padrão LIVRE");
        
        Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            this.categoriaRepository.delete(categoriaOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}/videos")
    public Page<VideoResponse> buscarVideosPorCategoriaId(@PathVariable("id") Long categoriaId, Pageable pageable) {
        List<VideoResponse> videosPorCategoriaId = this.videoRepository
                .findAllByCategoriaId(categoriaId, pageable)
                .stream()
                .map(VideoResponse::new)
                .collect(Collectors.toList());
        return new PageImpl<>(videosPorCategoriaId);
    }
}
