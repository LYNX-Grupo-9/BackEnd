package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.request.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ProcessoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.model.Evento;
import br.com.exemplo.crudusuariospring.model.Processo;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.EventoRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessoServiceTest {

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private ProcessoService processoService;

    @Test
    public void deveListarTodosOsProcessosPorIdAdvogado() {
        Long idAdvogado = 1L;

        Processo processo1 = new Processo();
        processo1.setIdProcesso(10L);
        processo1.setTitulo("Processo Trabalhista");

        Processo processo2 = new Processo();
        processo2.setIdProcesso(11L);
        processo2.setTitulo("Ação Civil");

        when(processoRepository.findByAdvogadoIdAdvogado(idAdvogado))
                .thenReturn(List.of(processo1, processo2));

        List<ProcessoResponse> resposta = processoService.listarTodosPorIdAdvogado(idAdvogado);

        assertEquals(2, resposta.size());
        assertEquals(10L, resposta.get(0).getIdProcesso());
        assertEquals("Processo Trabalhista", resposta.get(0).getTitulo());
        assertEquals(11L, resposta.get(1).getIdProcesso());
        assertEquals("Ação Civil", resposta.get(1).getTitulo());
    }

    @Test
    public void deveRetornarProcessoResponseQuandoIdExiste() {
        Integer idProcesso = 1;
        Processo processo = new Processo();
        processo.setIdProcesso(1L);
        processo.setTitulo("Processo Penal");

        when(processoRepository.findById(idProcesso)).thenReturn(Optional.of(processo));

        ProcessoResponse response = processoService.buscarPorId(idProcesso);

        assertNotNull(response);
        assertEquals(1L, response.getIdProcesso());
        assertEquals("Processo Penal", response.getTitulo());
    }

    @Test
    public void deveLancarExcecaoQuandoProcessoNaoForEncontrado() {
        Integer idProcesso = 99;
        when(processoRepository.findById(idProcesso)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            processoService.buscarPorId(idProcesso);
        });

        assertEquals("Processo não encontrado com ID: 99", exception.getMessage());
    }

    @Test
    public void deveRetornarListaDeProcessoResponsePorIdCliente() {
        Long idCliente = 1L;

        Processo processo1 = new Processo();
        processo1.setIdProcesso(101L);
        processo1.setTitulo("Processo 1");

        Processo processo2 = new Processo();
        processo2.setIdProcesso(102L);
        processo2.setTitulo("Processo 2");

        when(processoRepository.findByClienteIdCliente(idCliente))
                .thenReturn(List.of(processo1, processo2));

        List<ProcessoResponse> resultado = processoService.buscarPorIdCliente(idCliente);

        assertEquals(2, resultado.size());
        assertEquals(101L, resultado.get(0).getIdProcesso());
        assertEquals("Processo 1", resultado.get(0).getTitulo());
        assertEquals(102L, resultado.get(1).getIdProcesso());
        assertEquals("Processo 2", resultado.get(1).getTitulo());
    }

    @Test
    public void deveChamarDeleteByIdDoRepositorio() {
        Integer idProcesso = 1;

        processoService.excluirPorId(idProcesso);

        verify(processoRepository, times(1)).deleteById(idProcesso);
    }

    @Test
    public void deveAtualizarProcessoParcialmente() {
        Integer idProcesso = 1;

        Processo processoOriginal = new Processo();
        processoOriginal.setIdProcesso(idProcesso.longValue());
        processoOriginal.setTitulo("Titulo Antigo");
        processoOriginal.setNumeroProcesso("00001");
        processoOriginal.setStatus("Ativo");

        AtualizarProcessoRequest request = new AtualizarProcessoRequest();
        request.setTitulo("Titulo Novo");
        request.setStatus("Encerrado");
        request.setIdAdvogado(2);
        request.setIdCliente(3);

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(2);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(3);

        when(processoRepository.findById(idProcesso)).thenReturn(Optional.of(processoOriginal));
        when(advogadoRepository.findById(2)).thenReturn(Optional.of(advogado));
        when(clienteRepository.findById(3)).thenReturn(Optional.of(cliente));
        when(processoRepository.save(any(Processo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProcessoResponse response = processoService.atualizarProcessoParcialmente(idProcesso, request);

        assertNotNull(response);
        assertEquals("Titulo Novo", response.getTitulo());
        assertEquals("Encerrado", response.getStatus());
        assertEquals(2, response.getIdAdvogado());
        assertEquals(3, response.getIdCliente());

        verify(processoRepository).findById(idProcesso);
        verify(advogadoRepository).findById(2);
        verify(clienteRepository).findById(3);
        verify(processoRepository).save(any(Processo.class));
    }

    @Test
    public void deveLancarExcecaoQuandoProcessoNaoEncontradoParaAtualizacao() {
        Integer idProcesso = 99;
        AtualizarProcessoRequest request = new AtualizarProcessoRequest();

        when(processoRepository.findById(idProcesso)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            processoService.atualizarProcessoParcialmente(idProcesso, request);
        });

        assertEquals("Processo não encontrado para atualização", exception.getMessage());

        verify(processoRepository).findById(idProcesso);
        verify(processoRepository, never()).save(any(Processo.class));
    }

    @Test
    void deveRetornarOptionalVazioQuandoNaoExistiremEventosFuturos() {
        Integer idAdvogado = 1;

        when(eventoService.buscarProximoEvento(idAdvogado))
                .thenReturn(Optional.empty());

        Optional<EventoResponse> resultado = eventoService.buscarProximoEvento(idAdvogado);

        assertFalse(resultado.isPresent());
        verify(eventoService).buscarProximoEvento(idAdvogado);
    }

    @Test
    void deveListarProcessosAtivosPorAdvogado() {
        Long idAdvogado = 1L;

        Processo processo1 = new Processo();
        processo1.setIdProcesso(1L);
        processo1.setTitulo("Processo Ativo 1");
        processo1.setStatus("Ativo");

        Processo processo2 = new Processo();
        processo2.setIdProcesso(2L);
        processo2.setTitulo("Processo Ativo 2");
        processo2.setStatus("ATIVO");

        when(processoRepository.findByStatusIgnoreCaseAndAdvogadoIdAdvogado("Ativo", idAdvogado))
                .thenReturn(List.of(processo1, processo2));

        List<ProcessoResponse> resultado = processoService.listarProcessosAtivosPorAdvogado(idAdvogado);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getIdProcesso());
        assertEquals("Processo Ativo 1", resultado.get(0).getTitulo());
        assertEquals(2L, resultado.get(1).getIdProcesso());
        assertEquals("Processo Ativo 2", resultado.get(1).getTitulo());

        verify(processoRepository).findByStatusIgnoreCaseAndAdvogadoIdAdvogado("Ativo", idAdvogado);
    }
}