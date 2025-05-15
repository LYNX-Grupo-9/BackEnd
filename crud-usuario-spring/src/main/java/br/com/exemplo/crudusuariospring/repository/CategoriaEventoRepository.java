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

}
