package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.request.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.response.ProcessoResponse;
import br.com.exemplo.crudusuariospring.service.ProcessoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    private ProcessoResponse cadastrarProcesso(@RequestBody ProcessoRequest processoRequest) {
        return processoService.criarProcesso(processoRequest);
    }

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarPorAdvogado(@PathVariable Long idAdvogado) {
        List<ProcessoResponse> processos = processoService.listarTodosPorIdAdvogado(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    private ProcessoResponse buscarPorId(@PathVariable Integer id) {
        return processoService.buscarPorId(id);
    }

    @GetMapping("/cliente/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    private List<ProcessoResponse> buscarPorCliente(@PathVariable Long idCliente) {
        return processoService.buscarPorIdCliente(idCliente);
    }

    @DeleteMapping("/{idProcesso}")
    @SecurityRequirement(name = "Bearer")
    private void excluirProcesso(@PathVariable Integer idProcesso) {
        processoService.excluirPorId(idProcesso);
    }

    @PatchMapping("/{idProcesso}")
    @SecurityRequirement(name = "Bearer")
    private ProcessoResponse atualizarParcialmente(
            @PathVariable Integer idProcesso,
            @RequestBody AtualizarProcessoRequest request) {
        return processoService.atualizarProcessoParcialmente(idProcesso, request);
    }
}
