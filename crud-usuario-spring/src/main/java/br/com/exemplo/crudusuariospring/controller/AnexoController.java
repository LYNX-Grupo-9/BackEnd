package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.AnexoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AnexoResponse;
import br.com.exemplo.crudusuariospring.service.AnexoService;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anexos")
public class AnexoController {

    @Autowired
    private AnexoService anexoService;

    @PostMapping("")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AnexoResponse> criarAnexo(@RequestBody AnexoRequest request) {
        AnexoResponse response = anexoService.criarAnexo(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AnexoResponse>> getAnexosPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(anexoService.buscarPorIdCliente(idCliente));
    }

    @GetMapping("/processo/{idProcesso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AnexoResponse>> getAnexosPorProcesso(@PathVariable Integer idProcesso) {
        return ResponseEntity.ok(anexoService.buscarPorIdProcesso(idProcesso));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public void deletarAnexo(@PathVariable String id) {
        anexoService.deletarAnexo(id);
    }
}
