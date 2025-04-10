package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEventoRepository extends JpaRepository<CategoriaEvento, Long> {
}
