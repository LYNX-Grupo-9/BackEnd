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
    private SolicitacaoAgendamentoService solicitacaoAgendamentoService;

    @PostMapping("/solicitar")
    public ResponseEntity<SolicitacaoAgendamentoResponse> criarSolicitacao(@RequestBody @Valid SolicitacaoAgendamentoRequest request) {
        SolicitacaoAgendamentoResponse response = solicitacaoAgendamentoService.criarSolicitacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/buscar-por-email/{email}")
    @SecurityRequirement(name = "Bearer")
    public List<SolicitacaoAgendamentoResponse> buscarPorEmail(@PathVariable String email) {
        return solicitacaoAgendamentoService.buscarPorEmail(email);
    }
}
