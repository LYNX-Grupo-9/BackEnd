package br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.repository;

import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.entity.AdvogadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvogadoJpaRepository extends JpaRepository<AdvogadoEntity, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByRegistroOab(String registroOab);
}
