package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;
import br.com.exemplo.crudusuariospring.service.AdvogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advogados")
public class AdvogadoController {

    @Autowired
    private AdvogadoService service;

    @PostMapping
    public AdvogadoResponse cadastrarAdvogados(@RequestBody AdvogadoRequest request) {
        return service.salvar(request);
    }

    @GetMapping
    public List<AdvogadoResponse> listarAdvogados() {
        return service.listarTodos();
    }
}
