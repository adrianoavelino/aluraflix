package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository< Categoria, Long> {
}
