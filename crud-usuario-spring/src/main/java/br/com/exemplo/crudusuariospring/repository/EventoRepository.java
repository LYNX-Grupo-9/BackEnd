package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
