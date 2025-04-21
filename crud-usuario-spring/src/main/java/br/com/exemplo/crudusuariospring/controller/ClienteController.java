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
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponse> getClienteComQuantidadeProcessos(@PathVariable Integer id) {
        ClienteResponse clienteResponse = clienteService.buscarClienteComQuantidadeProcessos(id);
        return ResponseEntity.ok(clienteResponse);
    }
}
