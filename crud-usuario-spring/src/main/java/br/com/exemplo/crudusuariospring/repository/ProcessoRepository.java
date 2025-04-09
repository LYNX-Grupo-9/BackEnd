package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
}

