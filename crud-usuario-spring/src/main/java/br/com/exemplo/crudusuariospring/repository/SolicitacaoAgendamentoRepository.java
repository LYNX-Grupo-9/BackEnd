package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoAgendamentoRepository extends JpaRepository<SolicitacaoAgendamento, Integer> {
}
