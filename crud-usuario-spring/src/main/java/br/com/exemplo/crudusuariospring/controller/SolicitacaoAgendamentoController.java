package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.service.SolicitacaoAgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SolicitacaoAgendamentoController {

    @Autowired
    private SolicitacaoAgendamentoService solicitacaoAgendamentoService;

    @GetMapping("/api/solicitacoes")
    public ResponseEntity<List<SolicitacaoAgendamentoResponse>> listarSolicitacoes() {
        List<SolicitacaoAgendamentoResponse> solicitacoes = solicitacaoAgendamentoService.listarTodas();
        return ResponseEntity.ok(solicitacoes);
    }
}
