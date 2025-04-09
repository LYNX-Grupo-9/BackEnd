package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvogadoRepository extends JpaRepository<Advogado, Integer> {
    boolean existsByEmail(String email);
}
