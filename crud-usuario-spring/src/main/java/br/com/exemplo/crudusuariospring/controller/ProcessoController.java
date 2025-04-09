package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.ProcessoResponse;
import br.com.exemplo.crudusuariospring.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    private ProcessoResponse cadastrarProcesso(@RequestBody ProcessoRequest processoRequest){
        return processoService.salvar(processoRequest);
    }

    @GetMapping
    public List<ProcessoResponse> listarProcessos(){
        return processoService.listarTodos();
    }
}
