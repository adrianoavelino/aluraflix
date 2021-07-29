package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.controller.dto.VideoRequestSalvar;
import br.com.alura.aluraflix.controller.dto.VideoRequestAtualizar;
import br.com.alura.aluraflix.controller.dto.VideoResponse;
import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.VideoRepository;
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

    private  final VideoRepository repository;

    public VideoController(VideoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<VideoResponse> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable).map(VideoResponse::new);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VideoResponse> buscarPorId(@PathVariable("id") Long id) {
        Optional<Video> video = repository.findById(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(new VideoResponse(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VideoResponse> salvar(@RequestBody @Valid VideoRequestSalvar videoRequest, UriComponentsBuilder uriBuilder) {
        Video video = repository.save(videoRequest.converterParaVideo());
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoResponse(video));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Video> video = repository.findById(id);
        if (video.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<VideoResponse> atualizar(@RequestBody @Valid VideoRequestAtualizar videoRequest) {
        Optional<Video> video = repository.findById(videoRequest.getId());
        if (video.isPresent()) {
            repository.save(videoRequest.converterParaVideo());
            return ResponseEntity.ok().body(new VideoResponse(videoRequest.converterParaVideo()));
        }
        return ResponseEntity.notFound().build();
    }
}
