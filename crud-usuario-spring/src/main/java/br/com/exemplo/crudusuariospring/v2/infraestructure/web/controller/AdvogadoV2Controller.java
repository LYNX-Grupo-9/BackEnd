package br.com.exemplo.crudusuariospring.v2.infraestructure.web.controller;

import br.com.exemplo.crudusuariospring.v2.core.application.dto.command.CriarAdvogadoCommand;
import br.com.exemplo.crudusuariospring.v2.core.application.dto.response.CriarAdvogadoResponse;
import br.com.exemplo.crudusuariospring.v2.core.application.usecase.CriarAdvogadoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/advogados")
public class AdvogadoV2Controller {

    private CriarAdvogadoUseCase criarAdvogadoUseCase;

    public AdvogadoV2Controller(CriarAdvogadoUseCase criarAdvogadoUseCase) {
        this.criarAdvogadoUseCase = criarAdvogadoUseCase;
    }

    @PostMapping("/cadastrarV2")
    public ResponseEntity<CriarAdvogadoResponse> criar(@RequestBody CriarAdvogadoCommand command) {
        CriarAdvogadoResponse advogadoCriado = criarAdvogadoUseCase.excutar(command);
        return ResponseEntity.status(201).body(advogadoCriado);
    }

}
