package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    List<Processo> findByCliente_IdCliente(Integer idCliente);
}

