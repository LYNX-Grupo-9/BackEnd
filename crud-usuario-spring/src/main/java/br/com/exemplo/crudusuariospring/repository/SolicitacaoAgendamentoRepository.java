package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Evento;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitacaoAgendamentoRepository extends JpaRepository<SolicitacaoAgendamento, Integer> {
    List<SolicitacaoAgendamento> findByEmail(String email);
    List<SolicitacaoAgendamento> findByAdvogadoIdAdvogado(Integer idAdvogado);
}
