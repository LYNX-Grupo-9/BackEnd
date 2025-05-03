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
public class SolicitacaoAgendamentoController {

    @Autowired
    private SolicitacaoAgendamentoService solicitacaoAgendamentoService;

    @SecurityRequirement(name = "Bearer")
    @PostMapping("/api/solicitar-agendamento")
    public ResponseEntity<SolicitacaoAgendamentoResponse> criarSolicitacao(@RequestBody @Valid SolicitacaoAgendamentoRequest request) {
        SolicitacaoAgendamentoResponse response = solicitacaoAgendamentoService.criarSolicitacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/buscar-por-email")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<SolicitacaoAgendamentoResponse> buscarPorEmail(@RequestParam String email) {
        SolicitacaoAgendamentoResponse response = solicitacaoAgendamentoService.buscarPorEmail(email);
        return ResponseEntity.ok(response);
    }

}
