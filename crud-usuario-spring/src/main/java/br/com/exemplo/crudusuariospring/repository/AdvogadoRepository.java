package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvogadoRepository extends JpaRepository<Advogado, Integer> {
    boolean existsByEmail(String email);
    Optional<Advogado> findByEmail(String email);
}
