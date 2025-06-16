package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.SolicitacaoAgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolicitacaoAgendamentoServiceTest {

    @Mock
    private SolicitacaoAgendamentoRepository solicitacaoAgendamentoRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @InjectMocks
    private SolicitacaoAgendamentoService solicitacaoAgendamentoService;

    @Test
    void deveCriarSolicitacaoComSucesso() {
        SolicitacaoAgendamentoRequest request = new SolicitacaoAgendamentoRequest();
        request.setIdAdvogado(1);
        request.setNome("Teste");
        request.setTelefone("123456789");
        request.setEmail("teste@email.com");
        request.setAssunto("Teste");
        request.setDataSolicitacao(java.sql.Date.valueOf(LocalDate.now()));
        request.setHoraSolicitacao(LocalTime.of(10, 0));

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);

        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setIdSolicitacaoAgendamento(1L);
        solicitacao.setAdvogado(advogado);
        solicitacao.setVisualizado(false);

        when(advogadoRepository.findById(1)).thenReturn(Optional.of(advogado));
        when(solicitacaoAgendamentoRepository.save(any())).thenReturn(solicitacao);

        SolicitacaoAgendamentoResponse response = solicitacaoAgendamentoService.criarSolicitacao(request);

        assertNotNull(response);
        verify(advogadoRepository).findById(1);
        verify(solicitacaoAgendamentoRepository).save(any());
    }

    @Test
    void deveBuscarPorAdvogadoComSucesso() {
        Integer idAdvogado = 1;

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(idAdvogado);

        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setIdSolicitacaoAgendamento(1L);
        solicitacao.setAdvogado(advogado);
        solicitacao.setNome("Teste");
        solicitacao.setEmail("teste@email.com");
        solicitacao.setTelefone("123456789");
        solicitacao.setAssunto("Teste");
        solicitacao.setDataSolicitacao(java.sql.Date.valueOf(LocalDate.now()));
        solicitacao.setHoraSolicitacao(LocalTime.of(10, 0));
        solicitacao.setVisualizado(false);

        List<SolicitacaoAgendamento> solicitacoes = List.of(solicitacao);

        when(solicitacaoAgendamentoRepository.findByAdvogadoIdAdvogado(idAdvogado)).thenReturn(solicitacoes);

        List<SolicitacaoAgendamentoResponse> response = solicitacaoAgendamentoService.buscarPorAdvogado(idAdvogado);

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(solicitacaoAgendamentoRepository).findByAdvogadoIdAdvogado(idAdvogado);
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        Long idSolicitacao = 1L;

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);

        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setIdSolicitacaoAgendamento(idSolicitacao);
        solicitacao.setAdvogado(advogado);
        solicitacao.setNome("Teste");
        solicitacao.setEmail("teste@email.com");
        solicitacao.setTelefone("123456789");
        solicitacao.setAssunto("Teste");
        solicitacao.setDataSolicitacao(java.sql.Date.valueOf(LocalDate.now()));
        solicitacao.setHoraSolicitacao(LocalTime.of(10, 0));
        solicitacao.setVisualizado(false);

        when(solicitacaoAgendamentoRepository.findById(idSolicitacao.intValue())).thenReturn(Optional.of(solicitacao));

        SolicitacaoAgendamentoResponse response = solicitacaoAgendamentoService.buscarPorId(idSolicitacao.intValue());

        assertNotNull(response);
        verify(solicitacaoAgendamentoRepository).findById(idSolicitacao.intValue());
    }

    @Test
    void deveLancarExcecaoQuandoSolicitacaoNaoEncontrada() {
        Integer idSolicitacao = 1;
        when(solicitacaoAgendamentoRepository.findById(idSolicitacao)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> solicitacaoAgendamentoService.marcarComoVisualizado(idSolicitacao));
        assertThrows(RuntimeException.class, () -> solicitacaoAgendamentoService.buscarPorId(idSolicitacao));
    }
}