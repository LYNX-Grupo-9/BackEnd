package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Integer> {
    List<Anexo> findByCliente_IdCliente(Integer idCliente);
    List<Anexo> findByProcesso_IdProcesso(Integer processoIdProcesso);
}
