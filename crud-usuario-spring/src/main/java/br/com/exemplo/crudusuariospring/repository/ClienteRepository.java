package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);
    Optional<Cliente> findByNome(String nome);
    List<Cliente> findAllByOrderByNomeAsc();
    List<Cliente> findAllByOrderByNaturalidadeAsc();
    List<Cliente> findAllByOrderByDataNascimentoAsc();

    @Query("SELECT c FROM Cliente c LEFT JOIN c.processos p GROUP BY c ORDER BY COUNT(p) DESC")
    List<Cliente> ordenarPorQuantidadeProcessos();

    List<Cliente> buscarPorTexto(@Param("termo") String termo);
}
