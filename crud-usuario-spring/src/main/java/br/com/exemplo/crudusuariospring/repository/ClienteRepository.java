package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByEmail(String email);
}
