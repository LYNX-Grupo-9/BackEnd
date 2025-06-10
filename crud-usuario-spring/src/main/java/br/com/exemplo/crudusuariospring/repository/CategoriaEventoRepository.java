package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaEventoRepository extends JpaRepository<CategoriaEvento, Long> {
    Optional<CategoriaEvento> findByNome(String nomeEvento);

    List<CategoriaEvento> findByAdvogadoIdAdvogado(Integer idAdvogado);

    Optional<CategoriaEvento> findById(Long idCategoria);

    @Query("SELECT c.nome, COUNT(e), c.cor FROM CategoriaEvento c " +
            "LEFT JOIN c.eventos e " +
            "WHERE c.advogado.idAdvogado = :idAdvogado " +
            "GROUP BY c.nome, c.cor")
    List<Object[]> contarCategoriasAgrupadasPorNome(@Param("idAdvogado") Long idAdvogado);
}
