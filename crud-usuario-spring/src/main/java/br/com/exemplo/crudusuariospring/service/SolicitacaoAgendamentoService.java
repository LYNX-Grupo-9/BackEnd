package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.SolicitacaoAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SolicitacaoAgendamentoService {

    @Autowired
    private SolicitacaoAgendamentoRepository solicitacaoAgendamentoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    public SolicitacaoAgendamentoResponse criarSolicitacao(SolicitacaoAgendamentoRequest request) {
        Advogado advogado = advogadoRepository.findById(request.getIdAdvogado())
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado"));

        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setNome(request.getNome());
        solicitacao.setTelefone(request.getTelefone());
        solicitacao.setEmail(request.getEmail());
        solicitacao.setAssunto(request.getAssunto());
        solicitacao.setDataSolicitacao(request.getDataSolicitacao());
        solicitacao.setHoraSolicitacao(request.getHoraSolicitacao());
        solicitacao.setVisualizado(false);
        solicitacao.setAdvogado(advogado);

        SolicitacaoAgendamento salvaSolicitacao = solicitacaoAgendamentoRepository.save(solicitacao);

        return new SolicitacaoAgendamentoResponse(salvaSolicitacao);
    }

    public SolicitacaoAgendamentoResponse marcarComoVisualizado(Integer idSolicitacao) {
        Optional<SolicitacaoAgendamento> optionalSolicitacao = solicitacaoAgendamentoRepository.findById(idSolicitacao);

        if (optionalSolicitacao.isEmpty()) {
            throw new RuntimeException("Solicitação de agendamento não encontrada com ID: " + idSolicitacao);
        }

        SolicitacaoAgendamento solicitacao = optionalSolicitacao.get();
        solicitacao.setVisualizado(true);
        solicitacaoAgendamentoRepository.save(solicitacao);

        return new SolicitacaoAgendamentoResponse(solicitacao);
    }

    public List<SolicitacaoAgendamentoResponse> buscarPorAdvogado(Integer idAdvogado) {
        List<SolicitacaoAgendamento> lista = solicitacaoAgendamentoRepository.findByAdvogadoIdAdvogado(idAdvogado);
        return lista.stream()
                .map(SolicitacaoAgendamentoResponse::new)
                .collect(Collectors.toList());
    }

    public SolicitacaoAgendamentoResponse buscarPorId(Integer idSolicitacao) {
        SolicitacaoAgendamento solicitacao = solicitacaoAgendamentoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new RuntimeException("Solicitação de agendamento não encontrada com ID: " + idSolicitacao));

        return new SolicitacaoAgendamentoResponse(solicitacao);
    }
}