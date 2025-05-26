package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.SolicitacaoAgendamentoRequest;
import br.com.exemplo.crudusuariospring.dto.response.SolicitacaoAgendamentoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.SolicitacaoAgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class SolicitacaoAgendamentoServiceTest {

    @Mock
    private SolicitacaoAgendamentoRepository solicitacaoAgendamentoRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @InjectMocks
    private SolicitacaoAgendamentoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarSolicitacaoComSucesso() {
        SolicitacaoAgendamentoRequest request = new SolicitacaoAgendamentoRequest();
        request.setNome("Lucas");
        request.setTelefone("11999999999");
        request.setEmail("lucas@email.com");
        request.setAssunto("Consulta jurídica");
        request.setDataSolicitacao(LocalDate.now());
        request.setIdAdvogado(1);

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);

        SolicitacaoAgendamento solicitacaoSalva = new SolicitacaoAgendamento();
        solicitacaoSalva.setIdSolicitacaoAgendamento(10L);
        solicitacaoSalva.setNome(request.getNome());
        solicitacaoSalva.setTelefone(request.getTelefone());
        solicitacaoSalva.setEmail(request.getEmail());
        solicitacaoSalva.setAssunto(request.getAssunto());
        solicitacaoSalva.setDataSolicitacao(request.getDataSolicitacao());
        solicitacaoSalva.setAdvogado(advogado);

        when(advogadoRepository.findById(1)).thenReturn(Optional.of(advogado));
        when(solicitacaoAgendamentoRepository.save(any())).thenReturn(solicitacaoSalva);

        SolicitacaoAgendamentoResponse response = service.criarSolicitacao(request);

        assertNotNull(response);
        assertEquals("Lucas", response.getNome());
        assertEquals("lucas@email.com", response.getEmail());
        verify(solicitacaoAgendamentoRepository, times(1)).save(any());
    }

    @Test
    void testBuscarPorEmailComSolicitacoes() {
        String email = "lucas@email.com";

        SolicitacaoAgendamento solicitacao = new SolicitacaoAgendamento();
        solicitacao.setEmail(email);
        solicitacao.setNome("Lucas");

        when(solicitacaoAgendamentoRepository.findByEmail(email)).thenReturn(List.of(solicitacao));

        List<SolicitacaoAgendamentoResponse> responses = service.buscarPorEmail(email);

        assertEquals(1, responses.size());
        assertEquals("Lucas", responses.get(0).getNome());
        verify(solicitacaoAgendamentoRepository, times(1)).findByEmail(email);
    }

    @Test
    void testBuscarPorEmailSemSolicitacoes() {
        String email = "naoexiste@email.com";

        when(solicitacaoAgendamentoRepository.findByEmail(email)).thenReturn(List.of());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarPorEmail(email);
        });

        assertEquals("Solicitação não encontrada para o email: " + email, exception.getMessage());
        verify(solicitacaoAgendamentoRepository, times(1)).findByEmail(email);
    }
}
