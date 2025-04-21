package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.dto.response.ProcessoResponse;
import br.com.exemplo.crudusuariospring.model.Processo;
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
    private ProcessoResponse cadastrarProcesso(@RequestBody ProcessoRequest processoRequest){
        return processoService.salvar(processoRequest);
    }

    @GetMapping
    public List<ProcessoResponse> listarProcessos(){
        return processoService.listarTodos();
    }

    @GetMapping("/cliente/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> buscarPorCliente(@PathVariable Integer idCliente) {
        List<Processo> processos = processoService.listarPorCliente(idCliente);

        List<ProcessoResponse> resposta = processos.stream().map(processo -> {
            ProcessoResponse processoR = new ProcessoResponse(processo);

            if (processo.getAdvogado() != null) {
                processoR.setNomeAdvogado(processo.getAdvogado().getNome());
            }

            if (processo.getCliente() != null) {
                processoR.setNomeCliente(processo.getCliente().getNome());
            }

            return processoR;
        }).toList();

        return ResponseEntity.ok(resposta);
    }
}
