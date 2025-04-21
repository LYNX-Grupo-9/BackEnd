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
            ProcessoResponse pr = new ProcessoResponse();
            pr.setIdProcesso(processo.getIdProcesso());
            pr.setNumeroProcesso(processo.getNumeroProcesso());
            pr.setDescricao(processo.getDescricao());
            pr.setStatus(processo.getStatus());

            // Advogado
            if (processo.getAdvogado() != null) {
                AdvogadoResponse adv = new AdvogadoResponse();
                adv.setIdAdvogado(processo.getAdvogado().getIdAdvogado());
                adv.setNome(processo.getAdvogado().getNome());
                adv.setEmail(processo.getAdvogado().getEmail());
                adv.setRegistroOab(processo.getAdvogado().getRegistroOab());
                pr.setAdvogado(adv);
            }

            // Cliente
            if (processo.getCliente() != null) {
                ClienteResponse cli = new ClienteResponse();
                cli.setIdCliente(processo.getCliente().getIdCliente());
                cli.setNome(processo.getCliente().getNome());
                cli.setEmail(processo.getCliente().getEmail());
                cli.setTelefone(processo.getCliente().getTelefone());
                if (processo.getCliente().getAdvogado() != null) {
                    cli.setNomeAdvogado(processo.getCliente().getAdvogado().getNome());
                }
                pr.setCliente(cli);
            }

            return pr;
        }).toList();

        return ResponseEntity.ok(resposta);
    }
}
