package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarClienteRequest;
import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteProcessoEventoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.observer.event.ClienteCadastradoEvent;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @Autowired
    private ApplicationEventPublisher eventPublisher;

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
        cliente.setCnh(request.getCnh());
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
        response.setCnh(salvo.getCnh());
        response.setNaturalidade(salvo.getNaturalidade());
        response.setDataNascimento(salvo.getDataNascimento());
        response.setAdvogadoResponsavel(advogado.getNome());

        String nomeCliente = salvo.getNome();

        return response;
    } // FEITO

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
    } // FEITO

    public List<ClienteResponse> listarPorAdvogado(Integer idAdvogado) {
        return clienteRepository.findByAdvogadoIdAdvogado(idAdvogado).stream().map(c -> {
            ClienteResponse response = new ClienteResponse();
            response.setIdCliente(c.getIdCliente());
            response.setNome(c.getNome());
            response.setEmail(c.getEmail());
            response.setDocumento(c.getDocumento());
            response.setTipoDocumento(c.getTipoDocumento());
            response.setTelefone(c.getTelefone());
            response.setEndereco(c.getEndereco());
            response.setEstadoCivil(c.getEstadoCivil());
            response.setGenero(c.getGenero());
            response.setProfissao(c.getProfissao());
            response.setPassaporte(c.getPassaporte());
            response.setCnh(c.getCnh());
            response.setNaturalidade(c.getNaturalidade());
            response.setDataNascimento(c.getDataNascimento());
            response.setQtdProcessos(c.getQtdProcessos());
            response.setAdvogadoResponsavel(
                    c.getAdvogado() != null ? c.getAdvogado().getNome() : "Não atribuído"
            );
            return response;
        }).collect(Collectors.toList());
    } // FEITO

    public ClienteResponse buscarClienteComQuantidadeProcessos(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Long qtdProcessos = processoRepository.countByCliente_IdCliente(idCliente);

        ClienteResponse response = new ClienteResponse();
        response.setIdCliente(cliente.getIdCliente());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setTelefone(cliente.getTelefone());
        response.setEndereco(cliente.getEndereco());
        response.setEstadoCivil(cliente.getEstadoCivil());
        response.setGenero(cliente.getGenero());
        response.setProfissao(cliente.getProfissao());
        response.setPassaporte(cliente.getPassaporte());
        response.setCnh(cliente.getCnh());
        response.setNaturalidade(cliente.getNaturalidade());
        response.setDataNascimento(cliente.getDataNascimento());
        response.setAdvogadoResponsavel(cliente.getAdvogado() != null ? cliente.getAdvogado().getNome() : "Não atribuído");
        response.setQtdProcessos(qtdProcessos);

        return response;
    } // FEITO

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

    public List<ClienteResponse> listarOrdenadoPorNome(Integer idAdvogado) {
        return clienteRepository.findByAdvogadoIdAdvogadoOrderByNomeAsc(idAdvogado).stream().map(this::mapToResponse).collect(Collectors.toList());
    } // FEITO

    public List<ClienteResponse> listarOrdenadoPorNaturalidade(Integer idAdvogado) {
        return clienteRepository.findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(idAdvogado).stream().map(this::mapToResponse).collect(Collectors.toList());
    } // FEITO

    public List<ClienteResponse> listarOrdenadoPorDataNascimento(Integer idAdvogado) {
        return clienteRepository.findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(idAdvogado).stream().map(this::mapToResponse).collect(Collectors.toList());
    } // FEITO

    public List<ClienteResponse> listarOrdenadoPorQuantidadeProcessos(Integer idAdvogado) {
        return clienteRepository.ordenarPorQuantidadeProcessos(idAdvogado).stream().map(cliente -> {
            ClienteResponse response = mapToResponse(cliente);
            Long qtd = processoRepository.countByCliente_IdCliente(cliente.getIdCliente());
            response.setQtdProcessos(qtd);
            return response;
        }).collect(Collectors.toList());
    } // FEITO

    public List<ClienteResponse> buscarPorTexto(String termo, Integer idAvogado) {
        List<Cliente> clientes = clienteRepository.buscarPorNomeEmailTelefonePorAdvogado(termo, idAvogado);
        return clientes.stream().map(this::mapToResponse).collect(Collectors.toList());
    } // FEITO

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
                    ClienteProcessoEventoResponse.EventoResponse eventoResponse = new ClienteProcessoEventoResponse.EventoResponse();
                    eventoResponse.setIdEvento(evento.getIdEvento());
                    eventoResponse.setDataEvento(evento.getDataReuniao());
                    eventoResponse.setHoraInicio(evento.getHoraInicio());
                    eventoResponse.setHoraFim(evento.getHoraFim());
                    eventoResponse.setTitulo(evento.getNome());
                    eventoResponse.setTipo(evento.getCategoria() != null ? evento.getCategoria().getNome() : null);
                    return eventoResponse;
                }).toList();
        clienteDados.setEventos(eventosDTO);

        return clienteDados;
    }

    public ClienteResponse atualizarCliente(Integer idCliente, AtualizarClienteRequest request) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Advogado advogado = advogadoRepository.findById(request.getIdAdvogado())
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));

        if (request.getNome() != null) cliente.setNome(request.getNome());
        if (request.getDocumento() != null) cliente.setDocumento(request.getDocumento());
        if (request.getEmail() != null) cliente.setEmail(request.getEmail());
        if (request.getTelefone() != null) cliente.setTelefone(request.getTelefone());
        if (request.getEndereco() != null) cliente.setEndereco(request.getEndereco());
        if (request.getEstadoCivil() != null) cliente.setEstadoCivil(request.getEstadoCivil());
        if (request.getGenero() != null) cliente.setGenero(request.getGenero());
        if (request.getProfissao() != null) cliente.setProfissao(request.getProfissao());
        if (request.getPassaporte() != null) cliente.setPassaporte(request.getPassaporte());
        if (request.getCnh() != null) cliente.setCnh(request.getCnh());
        if (request.getNaturalidade() != null) cliente.setNaturalidade(request.getNaturalidade());
        if (request.getDataNascimento() != null) cliente.setDataNascimento(request.getDataNascimento());
        if (request.getIdAdvogado() != null) cliente.setAdvogado(advogado);

        Cliente atualizado = clienteRepository.save(cliente);

        eventPublisher.publishEvent(new ClienteCadastradoEvent(this, atualizado));

        return mapToResponse(atualizado);
    } // FEITO

    public Long contarClientesPorAdvogado(Integer idAdvogado) {
        advogadoRepository.findById(idAdvogado)
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado."));

        return clienteRepository.countByAdvogadoIdAdvogado(idAdvogado);
    } // FEITO
}
