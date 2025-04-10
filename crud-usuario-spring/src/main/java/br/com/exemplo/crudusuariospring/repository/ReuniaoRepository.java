package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {
}
