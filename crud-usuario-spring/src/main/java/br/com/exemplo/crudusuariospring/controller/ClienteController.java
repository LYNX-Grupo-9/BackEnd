package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteProcessoEventoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import br.com.exemplo.crudusuariospring.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @PostMapping("/cadastrar")
    public ClienteResponse cadastrarClientes(@RequestBody ClienteRequest request) {
        return clienteService.salvar(request);
    }

    @GetMapping("/listar")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/listarPorAdvogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> listarPorAdvogado(@PathVariable Integer idAdvogado) {
        List<ClienteResponse> clientes = clienteService.listarPorAdvogado(idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponse> getClienteComQuantidadeProcessos(@PathVariable Integer id) {
        ClienteResponse clienteResponse = clienteService.buscarClienteComQuantidadeProcessos(id);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping("/ordenado-nome")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorNome(Integer idAdvogado) {
        return clienteService.listarOrdenadoPorNome(idAdvogado);
    }

    @GetMapping("/ordenado-naturalidade")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorNaturalidade(Integer idAdvogado) {
        return clienteService.listarOrdenadoPorNaturalidade(idAdvogado);
    }

    @GetMapping("/ordenado-nascimento")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorNascimento(Integer idAdvogado) {
        return clienteService.listarOrdenadoPorDataNascimento(idAdvogado);
    }

    @GetMapping("/ordenado-processos")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorQtdProcessos(Integer idAdvogado) {
        return clienteService.listarOrdenadoPorQuantidadeProcessos(idAdvogado);
    }

    @GetMapping("/buscar")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> buscarPorTexto(@RequestParam String termo, Integer idAdvogado) {
        return clienteService.buscarPorTexto(termo, idAdvogado);
    }

    @GetMapping("/{id}/dados-completo")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteProcessoEventoResponse> buscarClienteCompleto(@PathVariable Integer id) {
        ClienteProcessoEventoResponse clienteDados = clienteService.buscarDadosCliente(id);
        return ResponseEntity.ok(clienteDados);
    }
}
