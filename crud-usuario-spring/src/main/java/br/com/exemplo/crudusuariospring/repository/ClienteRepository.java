package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);
    Optional<Cliente> findByNome(String nome);
}
