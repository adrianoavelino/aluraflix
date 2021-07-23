package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.VideoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/videos")
public class VideoController {

    private  final VideoRepository repository;

    public VideoController(VideoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Video> listarTodosVideos() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Video> listarVideoPorId(@PathVariable("id") Long id) {
        Optional<Video> video = repository.findById(id);
        if (video.isPresent()) return ResponseEntity.ok(video.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Video> salvar(@RequestBody @Valid Video video, UriComponentsBuilder uriBuilder) {
        repository.save(video);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(video);
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
    public ResponseEntity<Video> atualizar(@RequestBody @Valid Video video) {
        Optional<Video> videoSalvo = repository.findById(video.getId());
        if (videoSalvo.isPresent()) {
            repository.save(video);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
