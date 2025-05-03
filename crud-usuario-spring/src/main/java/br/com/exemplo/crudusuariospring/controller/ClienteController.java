package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
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

    @PostMapping
    public ClienteResponse cadastrarClientes(@RequestBody ClienteRequest request) {
        return clienteService.salvar(request);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponse> getClienteComQuantidadeProcessos(@PathVariable Integer id) {
        ClienteResponse clienteResponse = clienteService.buscarClienteComQuantidadeProcessos(id);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping("/ordenado-nome")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorNome() {
        return clienteService.listarOrdenadoPorNome();
    }

    @GetMapping("/ordenado-naturalidade")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorNaturalidade() {
        return clienteService.listarOrdenadoPorNaturalidade();
    }

    @GetMapping("/ordenado-nascimento")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorNascimento() {
        return clienteService.listarOrdenadoPorDataNascimento();
    }

    @GetMapping("/ordenado-processos")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorQtdProcessos() {
        return clienteService.listarOrdenadoPorQuantidadeProcessos();
    }

    @GetMapping("/buscar")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> buscarPorTexto(@RequestParam String termo) {
        return clienteService.buscarPorTexto(termo);
    }
}
