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
import java.util.Map;

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
    private ProcessoResponse atualizarParcialmente(@PathVariable Integer idProcesso, @RequestBody AtualizarProcessoRequest request) {
        return processoService.atualizarProcessoParcialmente(idProcesso, request);
    }

    @GetMapping("/processosAtivos/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarProcessosAtivosPorAdvogado(
            @PathVariable Long idAdvogado) {
        List<ProcessoResponse> processos = processoService.listarProcessosAtivosPorAdvogado(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/contagem-por-status/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<String, Long>> contarProcessosPorStatusPorAdvogado(@PathVariable Long idAdvogado) {
        Map<String, Long> contagem = processoService.contarProcessosPorStatusPorAdvogado(idAdvogado);
        return ResponseEntity.ok(contagem);
    }

    @GetMapping("/quantidade-por-classe/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<String, Long>> contarPorClasseProcessual(@PathVariable Long idAdvogado) {
        Map<String, Long> resultado = processoService.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/processos/ordenado-por-numero")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorNumero(@RequestParam Long idAdvogado) {
        return ResponseEntity.ok(processoService.listarProcessosOrdenadosPorNumeroDeProcesso(idAdvogado));
    }

    @GetMapping("/processos/ordenado-por-valor")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorValor(@RequestParam Long idAdvogado) {
        return ResponseEntity.ok(processoService.listarProcessosOrdenadosPorValor(idAdvogado));
    }

    @GetMapping("/buscarPorTexto")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> buscarPorTexto(@RequestParam String termo, @RequestParam Long idAdvogado) {
        return ResponseEntity.ok(processoService.buscarPorTexto(termo, idAdvogado));
    }

    @GetMapping("/processos/ordenado-por-status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorStatus(@RequestParam Long idAdvogado) {
        return ResponseEntity.ok(processoService.listarProcessosOrdenadosPorStatus(idAdvogado));
    }

    @GetMapping("/processos/ordenado-por-nome-cliente")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorNomeCliente(@RequestParam Long idAdvogado) {
        List<ProcessoResponse> processos = processoService.listarProcessosOrdenadosPorNomeCliente(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/media-valor/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public Double obterValorMedioPorAdvogado(@PathVariable Long idAdvogado) {
        return processoService.calcularValorMedioProcessosPorAdvogado(idAdvogado);
    }
}
