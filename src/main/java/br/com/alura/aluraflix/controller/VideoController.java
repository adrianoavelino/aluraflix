package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.VideoRequestSalvar;
import br.com.alura.aluraflix.controller.dto.VideoRequestAtualizar;
import br.com.alura.aluraflix.controller.dto.VideoResponse;
import br.com.alura.aluraflix.entity.Categoria;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.CategoriaRepository;
import br.com.alura.aluraflix.repository.VideoRepository;
import br.com.alura.aluraflix.validacao.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/v1/videos")
public class VideoController {

    private  final VideoRepository videoRepository;
    private  final CategoriaRepository categoriaRepository;

    public VideoController(VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
        this.videoRepository = videoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public Page<VideoResponse> buscarTodos(Pageable pageable) {
        return videoRepository.findAll(pageable).map(VideoResponse::new);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VideoResponse> buscarPorId(@PathVariable("id") Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(new VideoResponse(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VideoResponse> salvar(@RequestBody @Valid VideoRequestSalvar videoRequest, UriComponentsBuilder uriBuilder) throws ResourceNotFound {
        Optional<Categoria> categoriaOptional = this.categoriaRepository.findById(videoRequest.getCategoriaId());
        if (categoriaOptional.isPresent()) {
            Video video = videoRepository.save(videoRequest.converterParaVideo(categoriaRepository));
            URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            return ResponseEntity.created(uri).body(new VideoResponse(video));
        }
        throw new ResourceNotFound("Categoria não encontrada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<VideoResponse> atualizar(@RequestBody @Valid VideoRequestAtualizar videoRequest) throws ResourceNotFound {
        Optional<Video> video = videoRepository.findById(videoRequest.getId());
        if (video.isPresent()) {
            videoRepository.save(videoRequest.converterParaVideo(categoriaRepository));
            VideoResponse response = new VideoResponse(videoRequest.converterParaVideo(categoriaRepository));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.notFound().build();
    }
}
