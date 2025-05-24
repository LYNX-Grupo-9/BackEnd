package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarEventoRequest;
import br.com.exemplo.crudusuariospring.dto.request.EventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.model.*;
import br.com.exemplo.crudusuariospring.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @InjectMocks
    private EventoService eventoService;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CategoriaEventoRepository categoriaEventoRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @Test
    void deveCriarEventoComDadosCorretosERetornarEventoResponse() {
        LocalDate localDate = LocalDate.of(2025, 5, 24);
        Date dataConvertida = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        EventoRequest request = new EventoRequest();
        request.setNome("Reunião Jurídica");
        request.setDescricao("Discussão sobre processo X");
        request.setHoraInicio(LocalTime.of(10, 0, 0));
        request.setHoraFim(LocalTime.of(11, 0, 0));
        request.setDataReuniao(dataConvertida);
        request.setLocal("Sala 101");
        request.setLinkReuniao("https://meet.com/reuniao");
        request.setIdAdvogado(1);
        request.setIdCliente(2);
        request.setIdCategoria(3L);
        request.setIdProcesso(4);

        Advogado advogado = new Advogado();
        Cliente cliente = new Cliente();
        CategoriaEvento categoria = new CategoriaEvento();
        Processo processo = new Processo();

        when(advogadoRepository.findById(1)).thenReturn(Optional.of(advogado));
        when(clienteRepository.findById(2)).thenReturn(Optional.of(cliente));
        when(categoriaEventoRepository.findById(3L)).thenReturn(Optional.of(categoria));
        when(processoRepository.findById(4)).thenReturn(Optional.of(processo));

        Evento eventoSalvo = new Evento();
        eventoSalvo.setIdEvento(100L);
        eventoSalvo.setNome(request.getNome());
        eventoSalvo.setDescricao(request.getDescricao());
        eventoSalvo.setHoraInicio(request.getHoraInicio());
        eventoSalvo.setHoraFim(request.getHoraFim());
        eventoSalvo.setDataReuniao(dataConvertida);
        eventoSalvo.setLocal(request.getLocal());
        eventoSalvo.setLinkReuniao(request.getLinkReuniao());
        eventoSalvo.setAdvogado(advogado);
        eventoSalvo.setCliente(cliente);
        eventoSalvo.setCategoria(categoria);
        eventoSalvo.setProcesso(processo);

        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoSalvo);

        EventoResponse response = eventoService.criarEvento(request);

        assertNotNull(response);
        assertEquals("Reunião Jurídica", response.getNome());
        assertEquals("Discussão sobre processo X", response.getDescricao());

        LocalTime horaInicio = LocalTime.parse(response.getHoraInicio());
        LocalTime horaFim = LocalTime.parse(response.getHoraFim());

        assertEquals(10, horaInicio.getHour());
        assertEquals(0, horaInicio.getMinute());

        assertEquals(11, horaFim.getHour());
        assertEquals(0, horaFim.getMinute());

        assertEquals(localDate, LocalDate.parse(response.getDataReuniao()));

        assertEquals("Sala 101", response.getLocal());
        assertEquals("https://meet.com/reuniao", response.getLinkReuniao());

        verify(eventoRepository).save(any(Evento.class));
    }

    @Test
    void deveRetornarEventoQuandoEncontrarPorId() {
        Long id = 1L;

        Evento evento = new Evento();
        evento.setIdEvento(id);
        evento.setNome("Evento Teste");

        when(eventoRepository.findById(id)).thenReturn(Optional.of(evento));

        EventoResponse response = eventoService.buscarPorId(id);

        assertNotNull(response);
        assertEquals(id, response.getIdEvento());
        assertEquals("Evento Teste", response.getNome());

        verify(eventoRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoEventoNaoEncontrado() {
        when(eventoRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            eventoService.buscarPorId(999L);
        });

        assertEquals("Evento não encontrado", excecao.getMessage());
    }

    @Test
    void deveRetornarListaDeEventoResponseParaAdvogadoExistente() {
        Integer idAdvogado = 1;

        Evento evento1 = new Evento();
        evento1.setIdEvento(101L);
        evento1.setNome("Evento 1");

        Evento evento2 = new Evento();
        evento2.setIdEvento(102L);
        evento2.setNome("Evento 2");

        List<Evento> eventos = List.of(evento1, evento2);

        when(eventoRepository.findByAdvogadoIdAdvogado(idAdvogado)).thenReturn(eventos);

        List<EventoResponse> resposta = eventoService.buscaEventoPorAdvogado(idAdvogado);

        assertNotNull(resposta);
        assertEquals(2, resposta.size());
        assertEquals("Evento 1", resposta.get(0).getNome());
        assertEquals("Evento 2", resposta.get(1).getNome());

        verify(eventoRepository).findByAdvogadoIdAdvogado(idAdvogado);
    }

    @Test
    void deveRetornarEventosNosProximosSeteDiasParaAdvogado() {
        Integer idAdvogado = 1;

        LocalDate hoje = LocalDate.now();
        LocalDate seteDiasDepois = hoje.plusDays(7);
        Date dataInicial = java.sql.Date.valueOf(hoje);
        Date dataFinal = java.sql.Date.valueOf(seteDiasDepois);

        Evento evento1 = new Evento();
        evento1.setIdEvento(201L);
        evento1.setNome("Evento Próximo 1");

        Evento evento2 = new Evento();
        evento2.setIdEvento(202L);
        evento2.setNome("Evento Próximo 2");

        List<Evento> eventos = List.of(evento1, evento2);

        when(eventoRepository.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(idAdvogado, dataInicial, dataFinal))
                .thenReturn(eventos);

        List<EventoResponse> resposta = eventoService.buscaEventoProximoSeteDias(idAdvogado);

        assertNotNull(resposta);
        assertEquals(2, resposta.size());
        assertEquals("Evento Próximo 1", resposta.get(0).getNome());
        assertEquals("Evento Próximo 2", resposta.get(1).getNome());

        verify(eventoRepository).findByAdvogadoIdAdvogadoAndDataReuniaoBetween(idAdvogado, dataInicial, dataFinal);
    }

    @Test
    void deveDeletarEventoQuandoEncontrado() {
        Long idEvento = 10L;

        Evento evento = new Evento();
        evento.setIdEvento(idEvento);

        when(eventoRepository.findById(idEvento)).thenReturn(Optional.of(evento));

        eventoService.deletarEvento(idEvento);

        verify(eventoRepository).findById(idEvento);
        verify(eventoRepository).delete(evento);
    }

    @Test
    void deveLancarExcecaoQuandoEventoNaoEncontradoAoDeletar() {
        Long idEvento = 10L;

        when(eventoRepository.findById(idEvento)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoService.deletarEvento(idEvento);
        });

        assertEquals("Evento não encontrado", exception.getMessage());

        verify(eventoRepository).findById(idEvento);
        verify(eventoRepository, never()).delete(any());
    }

    @Test
    void deveLancarExcecaoQuandoEventoNaoEncontradoAoAtualizar() {
        Long id = 1L;
        AtualizarEventoRequest request = new AtualizarEventoRequest();

        when(eventoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoService.atualizarParcialmente(id, request);
        });

        assertEquals("Evento não encontrado", exception.getMessage());

        verify(eventoRepository).findById(id);
        verifyNoMoreInteractions(eventoRepository);
        verifyNoInteractions(advogadoRepository, clienteRepository, categoriaEventoRepository, processoRepository);
    }
}
