package br.com.exemplo.crudusuariospring.repository;

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
    List<Cliente> findByAdvogadoIdAdvogadoOrderByNomeAsc(Integer idAdvogado);
    List<Cliente> findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(Integer idAdvogado);
    List<Cliente> findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(Integer idAvogado);

    List<Cliente> findByAdvogadoIdAdvogado(Integer idAdvogado);

    @Query("SELECT c FROM Cliente c LEFT JOIN c.processos p GROUP BY c ORDER BY COUNT(p) DESC")
    List<Cliente> ordenarPorQuantidadeProcessos(@Param("idAdvogado") Integer idAdvogado);

    List<Cliente> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefoneContaining(String nome, String email, String telefone);

    @Query("SELECT c FROM Cliente c WHERE (LOWER(c.nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR c.telefone LIKE CONCAT('%', :searchTerm, '%')) " +
            "AND c.advogado.idAdvogado = :idAdvogado")
    List<Cliente> buscarPorNomeEmailTelefonePorAdvogado(@Param("searchTerm") String searchTerm, @Param("idAdvogado") Integer idAdvogado);

}
