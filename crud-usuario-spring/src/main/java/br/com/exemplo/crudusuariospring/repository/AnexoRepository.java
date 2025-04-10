package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Integer> {
}
