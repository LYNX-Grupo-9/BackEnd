package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
