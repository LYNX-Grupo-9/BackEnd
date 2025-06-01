package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarEventoRequest;
import br.com.exemplo.crudusuariospring.dto.request.EventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.model.*;
import br.com.exemplo.crudusuariospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
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
        evento.setHoraInicio(request.getHoraInicio());
        evento.setHoraFim(request.getHoraFim());
        evento.setDataReuniao(request.getDataReuniao());
        evento.setLocal(request.getLocal());
        evento.setLinkReuniao(request.getLinkReuniao());

        if (request.getDataReuniao() != null) {
            evento.setDataReuniao(request.getDataReuniao());
        }

        if (request.getHoraInicio() != null) {
            evento.setHoraInicio(request.getHoraInicio());
        }

        if (request.getHoraFim() != null) {
            evento.setHoraFim(request.getHoraFim());
        }

        Optional<Advogado> advogadoOpt = advogadoRepository.findById(request.getIdAdvogado());
        Optional<Cliente> clienteOpt = clienteRepository.findById(request.getIdCliente());
        Optional<CategoriaEvento> categoriaOpt = categoriaEventoRepository.findById(request.getIdCategoria());
        Optional<Processo> processoOpt = processoRepository.findById(request.getIdProcesso());

        advogadoOpt.ifPresent(evento::setAdvogado);
        clienteOpt.ifPresent(evento::setCliente);
        categoriaOpt.ifPresent(evento::setCategoria);
        processoOpt.ifPresent(evento::setProcesso);

        Evento eventoSalvo = eventoRepository.save(evento);
        return new EventoResponse(eventoSalvo);
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

    public List<EventoResponse> buscaEventoPorAdvogado(Integer idAdvogado) {
        List<Evento> eventos = eventoRepository.findByAdvogadoIdAdvogado(idAdvogado);
        return eventos.stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());
    }

    public List<EventoResponse> buscaEventoProximoSeteDias(Integer idAdvogado) {
        LocalDate hoje = LocalDate.now();
        LocalDate seteDiasDepois = hoje.plusDays(7);

        Date dataInicial = java.sql.Date.valueOf(hoje);
        Date dataFinal = java.sql.Date.valueOf(seteDiasDepois);

        List<Evento> eventos = eventoRepository.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                idAdvogado, dataInicial, dataFinal
        );

        return eventos.stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());
    }


    public void deletarEvento(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        eventoRepository.delete(evento);
    }

    public EventoResponse atualizarParcialmente(Long id, AtualizarEventoRequest request) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNaoEncontradoException("Evento não encontrado"));

        if (request.getNome() != null) {
            evento.setNome(request.getNome());
        }
        if (request.getDescricao() != null) {
            evento.setDescricao(request.getDescricao());
        }
        if (request.getDataReuniao() != null) {
            evento.setDataReuniao(request.getDataReuniao());
        }
        if (request.getHoraInicio() != null) {
            evento.setHoraInicio(request.getHoraInicio());
        }
        if (request.getHoraFim() != null) {
            evento.setHoraFim(request.getHoraFim());
        }
        if (request.getLocal() != null) {
            evento.setLocal(request.getLocal());
        }
        if (request.getLinkReuniao() != null) {
            evento.setLinkReuniao(request.getLinkReuniao());
        }
        if (request.getIdAdvogado() != null) {
            advogadoRepository.findByNome(request.getIdAdvogado())
                    .ifPresent(evento::setAdvogado);
        }
        if (request.getIdCliente() != null) {
            clienteRepository.findByNome(request.getIdCliente())
                    .ifPresent(evento::setCliente);
        }
        if (request.getIdCategoria() != null) {
            categoriaEventoRepository.findByNome(request.getIdCategoria())
                    .ifPresent(evento::setCategoria);
        }
        if (request.getIdProcesso() != null) {
            processoRepository.findByNumeroProcesso(request.getIdProcesso())
                    .ifPresent(evento::setProcesso);
        }

        Evento eventoAtualizado = eventoRepository.save(evento);
        return new EventoResponse(eventoAtualizado);
    }

    public List<EventoResponse> buscarEventosDoMesPorAdvogado(Integer idAdvogado) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date inicioMes = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date fimMes = calendar.getTime();

        List<Evento> eventos = eventoRepository.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(idAdvogado, inicioMes, fimMes);

        return eventos.stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());
    }

}
