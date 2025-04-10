package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import br.com.exemplo.crudusuariospring.repository.SolicitacaoAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoAgendamentoService {

    @Autowired
    private SolicitacaoAgendamentoRepository solicitacaoAgendamentoRepository;

    public SolicitacaoAgendamentoResponse criarSolicitacao(SolicitacaoAgendamentoRequest request) {
        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setNome(request.getNome());
        solicitacao.setTelefone(request.getTelefone());
        solicitacao.setEmail(request.getEmail());
        solicitacao.setAssunto(request.getAssunto());
        solicitacao.setMensagem(request.getMensagem());
        solicitacao.setDataSolicitacao(request.getDataSolicitacao());

        SolicitacaoAgendamento solicitacaoSalva = solicitacaoAgendamentoRepository.save(solicitacao);
        return new SolicitacaoAgendamentoResponse(solicitacaoSalva);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoAgendamentoResponse>> listarTodas() {
        List<SolicitacaoAgendamento> solicitacoes = solicitacaoAgendamentoRepository.findAll();
        List<SolicitacaoAgendamentoResponse> responseList = solicitacoes.stream()
                .map(SolicitacaoAgendamentoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

}