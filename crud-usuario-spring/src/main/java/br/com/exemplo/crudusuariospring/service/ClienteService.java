package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.observer.CadastroClienteSubject;
import br.com.exemplo.crudusuariospring.observer.EmailObserver;
import br.com.exemplo.crudusuariospring.observer.LogObserver;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    private CadastroClienteSubject subject;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public ClienteService() {
        subject = new CadastroClienteSubject();
        subject.adicionarObserver(new EmailObserver());
        subject.adicionarObserver(new LogObserver() );
    }

    public ClienteResponse salvar(ClienteRequest request) {
        var advogado = advogadoRepository.findById(request.getIdAdvogado())
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Cliente com este CPF já está cadastrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setAdvogado(advogado);

        Cliente salvo = repository.save(cliente);

        ClienteResponse response = new ClienteResponse();
        response.setIdCliente(salvo.getIdCliente());
        response.setNome(salvo.getNome());
        response.setEmail(salvo.getEmail());
        response.setTelefone(salvo.getTelefone());
        response.setNomeAdvogado(advogado.getNome());

        String nomeCliente = salvo.getNome();
        subject.notificarTodos("Novo cliente cadastrado: " + nomeCliente);

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

    public ClienteResponse buscarClienteComQuantidadeProcessos(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Contando os processos do cliente
        Integer qtdProcessos = processoRepository.countByCliente_IdCliente(idCliente);

        // Criando o ClienteResponse
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setIdCliente(cliente.getIdCliente());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setTelefone(cliente.getTelefone());
        clienteResponse.setNomeAdvogado(cliente.getAdvogado() != null ? cliente.getAdvogado().getNome() : "Não atribuído");
        clienteResponse.setQtdProcessos(qtdProcessos);

        return clienteResponse;
    }
}
