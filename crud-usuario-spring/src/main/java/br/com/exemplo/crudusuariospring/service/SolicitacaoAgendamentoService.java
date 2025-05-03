package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.SolicitacaoAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoAgendamentoService {

    @Autowired
    private SolicitacaoAgendamentoRepository solicitacaoAgendamentoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;


    public SolicitacaoAgendamentoResponse criarSolicitacao(SolicitacaoAgendamentoRequest request) {
        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setNome(request.getNome());
        solicitacao.setTelefone(request.getTelefone());
        solicitacao.setEmail(request.getEmail());
        solicitacao.setAssunto(request.getAssunto());
        solicitacao.setDataSolicitacao(request.getDataSolicitacao());

        Advogado advogado = advogadoRepository.findById(request.getIdAdvogado())
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado"));

        solicitacao.setAdvogado(advogado);

        SolicitacaoAgendamento salvo = solicitacaoAgendamentoRepository.save(solicitacao);
        return new SolicitacaoAgendamentoResponse(salvo);
    }

    public SolicitacaoAgendamentoResponse buscarPorEmail(String email) {
        SolicitacaoAgendamento solicitacao = solicitacaoAgendamentoRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada para o email: " + email));

        return new SolicitacaoAgendamentoResponse(solicitacao);
    }

}