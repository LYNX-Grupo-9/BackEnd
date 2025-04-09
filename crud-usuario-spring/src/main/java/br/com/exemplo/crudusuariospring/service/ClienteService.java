package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    public ClienteResponse salvar(ClienteRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Cliente com este CPF já está cadastrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());

        Cliente salvo = repository.save(cliente);

        ClienteResponse response = new ClienteResponse();
        response.setIdCliente(salvo.getIdCliente());
        response.setNome(salvo.getNome());
        response.setEmail(salvo.getEmail());
        response.setTelefone(salvo.getTelefone());

        return response;
    }

    public List<ClienteResponse> listarTodos() {
        return repository.findAll().stream().map(c -> {
            ClienteResponse response = new ClienteResponse();
            response.setIdCliente(c.getIdCliente());
            response.setNome(c.getNome());
            response.setEmail(c.getEmail());
            response.setTelefone(c.getTelefone());
            return response;
        }).collect(Collectors.toList());
    }

}
