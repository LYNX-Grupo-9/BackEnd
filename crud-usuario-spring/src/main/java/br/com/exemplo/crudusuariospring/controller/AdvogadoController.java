package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.AdvogadoMapper;
import br.com.exemplo.crudusuariospring.dto.request.AdvogadoLogin;
import br.com.exemplo.crudusuariospring.dto.request.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoToken;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.service.AdvogadoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/advogados")
public class AdvogadoController {

    @Autowired
    private AdvogadoService advogadoService;

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody AdvogadoRequest advogadoRequest) {
        final Advogado novoAdvogado = AdvogadoMapper.of(advogadoRequest);

        this.advogadoService.criarAdvogado(novoAdvogado);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AdvogadoToken> login(@RequestBody AdvogadoLogin advogadoLogin) {

        final Advogado advogado = AdvogadoMapper.of(advogadoLogin);
        AdvogadoToken advogadoToken = this.advogadoService.autenticar(advogado);

        return ResponseEntity.status(200).body(advogadoToken);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AdvogadoResponse>> listar() {
        List<AdvogadoResponse> advogados = this.advogadoService.listarTodosAdvogados();
        return ResponseEntity.ok(advogados);
    }
}
