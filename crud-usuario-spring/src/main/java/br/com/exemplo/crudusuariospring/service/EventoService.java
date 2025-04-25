package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.EventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.model.*;
import br.com.exemplo.crudusuariospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CategoriaEventoRepository categoriaEventoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public EventoResponse criarEvento(EventoRequest request) {
        Evento evento = new Evento();
        evento.setNome(request.getNome());
        evento.setDescricao(request.getDescricao());
        evento.setLocal(request.getLocal());
        evento.setLinkReuniao(request.getLinkReuniao());
        evento.setDataHora(request.getDataHora());

        Optional<Advogado> advogadoOpt = advogadoRepository.findByNome(request.getNomeAdvogado());
        Optional<Cliente> clienteOpt = clienteRepository.findByNome(request.getNomeCliente());
        Optional<CategoriaEvento> categoriaOpt = categoriaEventoRepository.findByNome(request.getNomeCategoria());
        Optional<Processo> processoOpt = processoRepository.findByNumeroProcesso(request.getNumeroProcesso());

        advogadoOpt.ifPresent(evento::setAdvogado);
        clienteOpt.ifPresent(evento::setCliente);
        categoriaOpt.ifPresent(evento::setCategoria);
        processoOpt.ifPresent(evento::setProcesso);

        Evento eventoSalvo = eventoRepository.save(evento);

        return new EventoResponse(eventoSalvo);
    }

    public List<EventoResponse> listarTodos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());
    }

    public EventoResponse buscarPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNaoEncontradoException("Evento não encontrado"));
        return new EventoResponse(evento);
    }

    private static class EventoNaoEncontradoException extends RuntimeException {
        public EventoNaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }
}
