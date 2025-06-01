package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
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
    public ResponseEntity<SolicitacaoAgendamentoResponse> criarSolicitacao(
            @Valid @RequestBody SolicitacaoAgendamentoRequest request) {
        SolicitacaoAgendamentoResponse response = service.criarSolicitacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/visualizar/{id}")
    @SecurityRequirement(name = "Baerer")
    public ResponseEntity<SolicitacaoAgendamentoResponse> marcarVisualizado(@PathVariable Integer id) {
        SolicitacaoAgendamentoResponse response = service.marcarComoVisualizado(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{idAdvogado}")
    @SecurityRequirement(name = "Baerer")
    public ResponseEntity<List<SolicitacaoAgendamentoResponse>> buscarPorAdvogado(@PathVariable Integer id) {
        List<SolicitacaoAgendamentoResponse> lista = service.buscarPorAdvogado(id);
        return ResponseEntity.ok(lista);
    }

}
