package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteProcessoEventoResponse;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    private CadastroClienteSubject subject;

    public ClienteService() {
        subject = new CadastroClienteSubject();
        subject.adicionarObserver(new EmailObserver());
        subject.adicionarObserver(new LogObserver());
    }

    public ClienteResponse salvar(ClienteRequest request) {
        var advogado = advogadoRepository.findById(request.getIdAdvogado())
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));

        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Cliente com este e-mail já está cadastrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setDocumento(request.getDocumento());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setEndereco(request.getEndereco());
        cliente.setEstadoCivil(request.getEstadoCivil());
        cliente.setGenero(request.getGenero());
        cliente.setProfissao(request.getProfissao());
        cliente.setPassaporte(request.getPassaporte());
        cliente.setCnh(request.getCnh()); // Mantendo cnh
        cliente.setNaturalidade(request.getNaturalidade());
        cliente.setDataNascimento(request.getDataNascimento());
        cliente.setAdvogado(advogado);

        Cliente salvo = clienteRepository.save(cliente);

        ClienteResponse response = new ClienteResponse();
        response.setIdCliente(salvo.getIdCliente());
        response.setNome(salvo.getNome());
        response.setEmail(salvo.getEmail());
        response.setTelefone(salvo.getTelefone());
        response.setEndereco(salvo.getEndereco());
        response.setEstadoCivil(salvo.getEstadoCivil());
        response.setGenero(salvo.getGenero());
        response.setProfissao(salvo.getProfissao());
        response.setPassaporte(salvo.getPassaporte());
        response.setCnh(salvo.getCnh()); // Mantendo cnh
        response.setNaturalidade(salvo.getNaturalidade());
        response.setDataNascimento(salvo.getDataNascimento());
        response.setAdvogadoResponsavel(advogado.getNome());

        String nomeCliente = salvo.getNome();
        subject.notificarTodos("Novo cliente cadastrado: " + nomeCliente);

        return response;
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream().map(c -> {
            ClienteResponse response = new ClienteResponse();
            response.setIdCliente(c.getIdCliente());
            response.setNome(c.getNome());
            response.setEmail(c.getEmail());
            response.setTelefone(c.getTelefone());
            response.setEndereco(c.getEndereco());
            response.setEstadoCivil(c.getEstadoCivil());
            response.setGenero(c.getGenero());
            response.setProfissao(c.getProfissao());
            response.setPassaporte(c.getPassaporte());
            response.setCnh(c.getCnh());
            response.setNaturalidade(c.getNaturalidade());
            response.setDataNascimento(c.getDataNascimento());
            response.setAdvogadoResponsavel(c.getAdvogado() != null ? c.getAdvogado().getNome() : "Não atribuído");
            return response;
        }).collect(Collectors.toList());
    }

    public ClienteResponse buscarClienteComQuantidadeProcessos(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Integer qtdProcessos = processoRepository.countByCliente_IdCliente(idCliente);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setIdCliente(cliente.getIdCliente());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setTelefone(cliente.getTelefone());
        clienteResponse.setEndereco(cliente.getEndereco());
        clienteResponse.setEstadoCivil(cliente.getEstadoCivil());
        clienteResponse.setGenero(cliente.getGenero());
        clienteResponse.setProfissao(cliente.getProfissao());
        clienteResponse.setPassaporte(cliente.getPassaporte());
        clienteResponse.setCnh(cliente.getCnh());
        clienteResponse.setNaturalidade(cliente.getNaturalidade());
        clienteResponse.setDataNascimento(cliente.getDataNascimento());
        clienteResponse.setAdvogadoResponsavel(cliente.getAdvogado() != null ? cliente.getAdvogado().getNome() : "Não atribuído");
        clienteResponse.setQtdProcessos(qtdProcessos);

        return clienteResponse;
    }

    private ClienteResponse mapToResponse(Cliente c) {
        ClienteResponse response = new ClienteResponse();
        response.setIdCliente(c.getIdCliente());
        response.setNome(c.getNome());
        response.setEmail(c.getEmail());
        response.setTelefone(c.getTelefone());
        response.setEndereco(c.getEndereco());
        response.setEstadoCivil(c.getEstadoCivil());
        response.setGenero(c.getGenero());
        response.setProfissao(c.getProfissao());
        response.setPassaporte(c.getPassaporte());
        response.setCnh(c.getCnh());
        response.setNaturalidade(c.getNaturalidade());
        response.setDataNascimento(c.getDataNascimento());
        response.setAdvogadoResponsavel(
                c.getAdvogado() != null ? c.getAdvogado().getNome() : "Não atribuído"
        );
        return response;
    }

    public List<ClienteResponse> listarOrdenadoPorNome() {
        return clienteRepository.findAllByOrderByNomeAsc().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ClienteResponse> listarOrdenadoPorNaturalidade() {
        return clienteRepository.findAllByOrderByNaturalidadeAsc().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ClienteResponse> listarOrdenadoPorDataNascimento() {
        return clienteRepository.findAllByOrderByDataNascimentoAsc().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ClienteResponse> listarOrdenadoPorQuantidadeProcessos() {
        return clienteRepository.ordenarPorQuantidadeProcessos().stream().map(cliente -> {
            ClienteResponse response = mapToResponse(cliente);
            Integer qtd = processoRepository.countByCliente_IdCliente(cliente.getIdCliente());
            response.setQtdProcessos(qtd);
            return response;
        }).collect(Collectors.toList());
    }

    public List<ClienteResponse> buscarPorTexto(String termo) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefoneContaining(termo, termo, termo);
        return clientes.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public ClienteProcessoEventoResponse buscarDadosCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        ClienteProcessoEventoResponse clienteDados = new ClienteProcessoEventoResponse();

        clienteDados.setIdCliente(cliente.getIdCliente());
        clienteDados.setNome(cliente.getNome());
        clienteDados.setDocumento(cliente.getDocumento());
        clienteDados.setTipoDocumento(cliente.getTipoDocumento());
        clienteDados.setEmail(cliente.getEmail());
        clienteDados.setTelefone(cliente.getTelefone());
        clienteDados.setEndereco(cliente.getEndereco());
        clienteDados.setGenero(cliente.getGenero());
        clienteDados.setDataNascimento(cliente.getDataNascimento());
        clienteDados.setEstadoCivil(cliente.getEstadoCivil());
        clienteDados.setProfissao(cliente.getProfissao());
        clienteDados.setPassaporte(cliente.getPassaporte());
        clienteDados.setCnh(cliente.getCnh());
        clienteDados.setNaturalidade(cliente.getNaturalidade());

        // Processos
        List<ClienteProcessoEventoResponse.ProcessoResponse> processosDTO = cliente.getProcessos().stream()
                .map(processo -> {
                    ClienteProcessoEventoResponse.ProcessoResponse p = new ClienteProcessoEventoResponse.ProcessoResponse();
                    p.setNumeroProcesso(processo.getNumeroProcesso());
                    return p;
                }).toList();
        clienteDados.setProcessos(processosDTO);

        // Eventos
        List<ClienteProcessoEventoResponse.EventoResponse> eventosDTO = cliente.getEventos().stream()
                .map(evento -> {
                    ClienteProcessoEventoResponse.EventoResponse e = new ClienteProcessoEventoResponse.EventoResponse();
                    e.setIdEvento(evento.getIdEvento());
                    e.setDataHora(evento.getDataHora());
                    e.setTitulo(evento.getNome());
                    e.setTipo(evento.getCategoria() != null ? evento.getCategoria().getNome() : null);
                    return e;
                }).toList();
        clienteDados.setEventos(eventosDTO);

        return clienteDados;
    }

}
