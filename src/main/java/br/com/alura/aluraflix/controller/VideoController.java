package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.entity.Video;
import br.com.alura.aluraflix.repository.VideoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
