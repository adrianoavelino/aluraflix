package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.CategoriaRequestAtualizar;
import br.com.alura.aluraflix.controller.dto.CategoriaRequestSalvar;
import br.com.alura.aluraflix.controller.dto.CategoriaResponse;
import br.com.alura.aluraflix.controller.dto.VideoResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.service.CategoriaService;
import br.com.alura.aluraflix.validacao.ActionNotAllowed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaRepository repository, CategoriaService categoriaService) {
        this.categoriaRepository = repository;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> buscarTodas(@PageableDefault(size = 5) Pageable pageable) {
        Page<Categoria> categorias = this.categoriaService.buscarTodas(pageable);
        Page<CategoriaResponse> categoriasResponse = categorias.map(CategoriaResponse::new);
        return ResponseEntity.ok(categoriasResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = this.categoriaService.buscarPorId(id);
        if (categoriaOptional.isPresent()) {
            CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaOptional.get());
            return ResponseEntity.ok(categoriaResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvar(
            @RequestBody @Valid CategoriaRequestSalvar categoriaRequest,
            UriComponentsBuilder uriBuilder) {
        Categoria categoria = this.categoriaService.salvar(categoriaRequest.converterParaCategoria());
        URI uri = uriBuilder.path("/v1/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaResponse(categoria));
    }

    @PutMapping
    public ResponseEntity<CategoriaResponse> atualizar(
            @RequestBody @Valid CategoriaRequestAtualizar categoriaRequest) {
        Categoria categoria = categoriaRequest.converterParaCategoria();
        Optional<Categoria> categoriaOptional = this.categoriaService.atualizar(categoria);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(new CategoriaResponse(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) throws ActionNotAllowed {
        Optional<?> categoriaOptional = this.categoriaService.deletar(id);
        if (categoriaOptional.isPresent()) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}/videos")
    public ResponseEntity<Page<VideoResponse>> buscarVideosPorCategoriaId(@PathVariable("id") Long categoriaId, @PageableDefault(size = 5) Pageable pageable) {
        Page<VideoResponse> videosResponse = this.categoriaService.buscarVideosPorCategoriaId(categoriaId, pageable);
        return ResponseEntity.ok(videosResponse);
    }
}
