package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.EventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.model.Evento;
import br.com.exemplo.crudusuariospring.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public EventoResponse criarEvento(EventoRequest request) {
        Evento evento = new Evento();
        evento.setTipo(request.getTipo());
        evento.setDescricao(request.getDescricao());
        evento.setDataHora(request.getDataHora());
        evento.setLocal(request.getLocal());

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
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return new EventoResponse(evento);
    }
}
