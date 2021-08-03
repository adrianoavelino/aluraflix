package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.entity.Categoria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Test
    public void deveSalvarUmaCategoria() {
        Categoria categoria = new Categoria("categoria1", "#cccccc");
        long totalDeCategoriasSalvaEsperada = this.repository.count() + 1;

        Categoria categoriaSalva = repository.save(categoria);

        Assertions.assertThat(totalDeCategoriasSalvaEsperada).isEqualTo(repository.count());
        Assertions.assertThat(categoriaSalva).usingRecursiveComparison().isEqualTo(categoria);
    }

    @Test
    public void deveBuscarTodasAsCategorias() {
        long totalDeCategorias = repository.count();
        Categoria categoria1 = new Categoria("categoria1", "#cccccc");
        Categoria categoria2 = new Categoria("categoria2", "#dddddd");
        Categoria categoria3 = new Categoria("categoria3", "#eeeeee");
        repository.save(categoria1);
        repository.save(categoria2);
        repository.save(categoria3);

        Assertions.assertThat(totalDeCategorias + 3).isEqualTo(repository.count());
    }

    @Test
    public void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria("categoria1", "#cccccc");
        Categoria categoriaSalva = repository.save(categoria);

        Optional<Categoria> categoriaOptional = repository.findById(categoriaSalva.getId());
        Assertions.assertThat(categoriaOptional.get()).usingRecursiveComparison().ignoringFields("videos").isEqualTo(categoriaSalva);
    }

    @Test
    public void deveAtualizarUmaCategoria() {
        Categoria categoria = new Categoria("categoria1", "#cccccc");
        repository.save(categoria);
        Categoria categoriaAlterada = new Categoria(categoria.getId(),"categoria1 alterada", "#ffffff");

        repository.save(categoriaAlterada);

        Categoria categoriaAtual = repository.findById(categoriaAlterada.getId()).get();
        Assertions.assertThat(categoriaAtual).usingRecursiveComparison().ignoringFields("videos").isEqualTo(categoriaAlterada);
    }

}