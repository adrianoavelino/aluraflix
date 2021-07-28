package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.CategoriaResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}