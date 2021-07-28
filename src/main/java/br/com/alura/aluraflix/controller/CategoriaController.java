package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.CategoriaRequestSalvar;
import br.com.alura.aluraflix.controller.dto.CategoriaResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public Page<CategoriaResponse> buscarTodas(Pageable pageable) {
        return this.repository.findAll(pageable).map(CategoriaResponse::new);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = this.repository.findById(id);
        if (categoriaOptional.isPresent()) return ResponseEntity.ok(new CategoriaResponse(categoriaOptional.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvar(@RequestBody @Valid CategoriaRequestSalvar categoriaRequest, UriComponentsBuilder uriBuilder) {
        Categoria categoria = this.repository.save(categoriaRequest.converterParaCategoria());
        URI uri = uriBuilder.path("/v1/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaResponse(categoria));
    }
}
