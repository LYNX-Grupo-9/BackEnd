package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.service.SolicitacaoAgendamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacao-agendamento")
public class SolicitacaoAgendamentoController {

    @Autowired
    private SolicitacaoAgendamentoService service;

    @PostMapping("/solicitar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<SolicitacaoAgendamentoResponse> criarSolicitacao(
            @Valid @RequestBody SolicitacaoAgendamentoRequest request) {
        SolicitacaoAgendamentoResponse response = service.criarSolicitacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }// FEITO

    @PutMapping("/visualizar/{idSolicitacao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<SolicitacaoAgendamentoResponse> marcarVisualizado(@PathVariable Integer idSolicitacao) {
        SolicitacaoAgendamentoResponse response = service.marcarComoVisualizado(idSolicitacao);
        return ResponseEntity.ok(response);
    }// FEITO

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<SolicitacaoAgendamentoResponse>> buscarPorAdvogado(@PathVariable Integer idAdvogado) {
        List<SolicitacaoAgendamentoResponse> lista = service.buscarPorAdvogado(idAdvogado);
        return ResponseEntity.ok(lista);
    }// FEITO

    @GetMapping("/solicitacao/{idSolicitacao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<SolicitacaoAgendamentoResponse> buscarPorId(@PathVariable Integer idSolicitacao) {
        SolicitacaoAgendamentoResponse response = service.buscarPorId(idSolicitacao);
        return ResponseEntity.ok(response);
    } // FEITO
}
