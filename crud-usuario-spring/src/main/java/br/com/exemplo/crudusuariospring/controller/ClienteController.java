package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ClienteResponse cadastrarClientes(@RequestBody ClienteRequest request) {
        return service.salvar(request);
    }

    @GetMapping
    public List<ClienteResponse> listarClientes() {
        return service.listarTodos();
    }

}
