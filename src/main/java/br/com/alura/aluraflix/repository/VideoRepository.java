package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.entity.Usuario;
import br.com.alura.aluraflix.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByCategoriaId(Long categoriaId, Pageable pageable);
    Page<Video> findByTitulo(String titulo, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM videos v ORDER BY id DESC LIMIT 10")
    List<Video> findLastTen();
}
