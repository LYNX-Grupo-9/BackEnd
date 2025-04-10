package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.response.AnexoResponse;
import br.com.exemplo.crudusuariospring.model.Anexo;
import br.com.exemplo.crudusuariospring.service.AnexoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anexos")
public class AnexoController {

    @Autowired
    private AnexoService anexoService;

    @PostMapping
    public ResponseEntity<AnexoResponse> criarAnexo(@RequestBody AnexoResponse anexoResponse) {
        Anexo anexo = new Anexo();
        anexo.setNomeAnexo(anexoResponse.getNomeAnexo());
        anexo.setLinkBucket(anexoResponse.getLinkBucket());

        Anexo anexoSalvo = anexoService.salvarAnexo(anexo);

        AnexoResponse response = new AnexoResponse();
        response.setIdAnexo(anexoSalvo.getIdAnexo());
        response.setNomeAnexo(anexoSalvo.getNomeAnexo());
        response.setLinkBucket(anexoSalvo.getLinkBucket());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnexoResponse>> pegarTodosAnexos() {
        List<Anexo> anexos = anexoService.pegarTodosAnexos();
        List<AnexoResponse> response = anexos.stream()
                .map(anexo -> {
                    AnexoResponse dto = new AnexoResponse();
                    dto.setIdAnexo(anexo.getIdAnexo());
                    dto.setNomeAnexo(anexo.getNomeAnexo());
                    dto.setLinkBucket(anexo.getLinkBucket());
                    return dto;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
