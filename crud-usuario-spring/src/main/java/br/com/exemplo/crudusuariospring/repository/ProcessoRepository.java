package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    List<Processo> findByAdvogadoIdAdvogado(Long idAdvogado);
    List<Processo> findByClienteIdCliente(Long idCliente);
    Long countByCliente_IdCliente(Integer idCliente);
    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
}

