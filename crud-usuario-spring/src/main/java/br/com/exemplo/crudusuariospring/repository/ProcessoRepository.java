package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    List<Processo> findByAdvogadoIdAdvogado(Long idAdvogado);
    List<Processo> findByClienteIdCliente(Long idCliente);
    Long countByCliente_IdCliente(Integer idCliente);
    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
    List<Processo> findByStatusIgnoreCaseAndAdvogadoIdAdvogado(String status, Long idAdvogado);
    List<Processo> findByAdvogadoIdAdvogadoOrderByNumeroProcessoAsc(Long idAdvogado);
    List<Processo> findByAdvogadoIdAdvogadoOrderByValorAsc(Long idAdvogado);
    List<Processo> findByAdvogadoIdAdvogadoOrderByStatusAsc(Long idAdvogado);

    @Query("SELECT p.classeProcessual, COUNT(p) FROM Processo p WHERE p.advogado.idAdvogado = :idAdvogado GROUP BY p.classeProcessual")
    List<Object[]> contarProcessosPorClasseProcessualPorAdvogado(@Param("idAdvogado") Long idAdvogado);

    @Query("SELECT p FROM Processo p JOIN p.cliente c WHERE p.advogado.idAdvogado = :idAdvogado ORDER BY c.nome ASC")
    List<Processo> buscarOrdenadoPorCliente(@Param("idAdvogado") Long idAdvogado);

    @Query("SELECT p FROM Processo p JOIN p.cliente c WHERE p.advogado.idAdvogado = :idAdvogado AND (" +
            "LOWER(p.numeroProcesso) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%'))" +
            ")")
    List<Processo> buscarPorNumeroTituloOuCliente(@Param("termo") String termo, @Param("idAdvogado") Long idAdvogado);
}

