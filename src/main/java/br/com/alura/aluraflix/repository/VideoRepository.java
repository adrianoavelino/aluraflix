package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByCategoriaId(Long categoriaId, Pageable pageable);
    Page<Video> findByTitulo(String titulo, Pageable pageable);
}
