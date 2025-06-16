package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AnexoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AnexoResponse;
import br.com.exemplo.crudusuariospring.model.Anexo;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.model.Processo;
import br.com.exemplo.crudusuariospring.repository.AnexoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnexoServiceTest {

    @Mock
    private AnexoRepository anexoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private AnexoService anexoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveCriarAnexoComClienteEProcesso() {
        AnexoRequest request = new AnexoRequest();
        request.setIdAnexo(1L);
        request.setNomeAnexo("documento.pdf");
        request.setIdItem("5");
        request.setIdCliente(1);
        request.setIdProcesso(2);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);

        Processo processo = new Processo();
        processo.setIdProcesso(2L);

        Anexo salvo = new Anexo();
        salvo.setIdAnexo(1L);
        salvo.setNomeAnexo("documento.pdf");
        salvo.setIdItem("1");
        salvo.setCliente(cliente);
        salvo.setProcesso(processo);

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(processoRepository.findById(2)).thenReturn(Optional.of(processo));
        when(anexoRepository.save(any(Anexo.class))).thenReturn(salvo);
        doNothing().when(eventPublisher).publishEvent(any());

        AnexoResponse response = anexoService.criarAnexo(request);

        assertNotNull(response);
        assertEquals(1L, response.getIdAnexo());
        assertEquals("documento.pdf", response.getNomeAnexo());
        verify(eventPublisher).publishEvent(any());
    }

    @Test
    public void deveLancarExcecaoSeClienteNaoEncontrado() {
        AnexoRequest request = new AnexoRequest();
        request.setIdCliente(99);

        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            anexoService.criarAnexo(request);
        });

        assertEquals("Cliente não encontrado", ex.getMessage());
    }

    @Test
    public void deveLancarExcecaoSeProcessoNaoEncontrado() {
        AnexoRequest request = new AnexoRequest();
        request.setIdProcesso(88);

        when(processoRepository.findById(88)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            anexoService.criarAnexo(request);
        });

        assertEquals("Processo não encontrado", ex.getMessage());
    }

    @Test
    public void deveBuscarAnexosPorIdCliente() {
        Integer idCliente = 1;

        Anexo anexo1 = new Anexo();
        anexo1.setIdAnexo(10L);
        anexo1.setNomeAnexo("arquivo1.pdf");

        Anexo anexo2 = new Anexo();
        anexo2.setIdAnexo(11L);
        anexo2.setNomeAnexo("arquivo2.pdf");

        when(anexoRepository.findByCliente_IdCliente(idCliente))
                .thenReturn(List.of(anexo1, anexo2));

        List<AnexoResponse> resposta = anexoService.buscarPorIdCliente(idCliente);

        assertEquals(2, resposta.size());
        assertEquals(10L, resposta.get(0).getIdAnexo());
        assertEquals("arquivo1.pdf", resposta.get(0).getNomeAnexo());
        assertEquals(11L, resposta.get(1).getIdAnexo());
        assertEquals("arquivo2.pdf", resposta.get(1).getNomeAnexo());
    }

    @Test
    public void deveBuscarAnexosPorIdProcesso() {
        Integer idProcesso = 5;

        Anexo anexo1 = new Anexo();
        anexo1.setIdAnexo(100L);
        anexo1.setNomeAnexo("documento1.pdf");

        Anexo anexo2 = new Anexo();
        anexo2.setIdAnexo(101L);
        anexo2.setNomeAnexo("documento2.pdf");

        when(anexoRepository.findByProcesso_IdProcesso(idProcesso))
                .thenReturn(List.of(anexo1, anexo2));

        List<AnexoResponse> resposta = anexoService.buscarPorIdProcesso(idProcesso);

        assertEquals(2, resposta.size());
        assertEquals(100L, resposta.get(0).getIdAnexo());
        assertEquals("documento1.pdf", resposta.get(0).getNomeAnexo());
        assertEquals(101L, resposta.get(1).getIdAnexo());
        assertEquals("documento2.pdf", resposta.get(1).getNomeAnexo());
    }

    @Test
    public void deveDeletarAnexoQuandoExistir() {
        String idItem = "123";

        when(anexoRepository.existsByIdItem(idItem)).thenReturn(true);
        doNothing().when(anexoRepository).deleteByIdItem(idItem);

        anexoService.deletarAnexo(idItem);

        verify(anexoRepository).existsByIdItem(idItem);
        verify(anexoRepository).deleteByIdItem(idItem);
    }

    @Test
    public void deveLancarExcecaoQuandoAnexoNaoExistir() {
        String idItem = "123";

        when(anexoRepository.existsByIdItem(idItem)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            anexoService.deletarAnexo(idItem);
        });

        assertEquals("Anexo com ID 123 não encontrado.", exception.getMessage());
        verify(anexoRepository).existsByIdItem(idItem);
        verify(anexoRepository, never()).deleteByIdItem(any());
    }

}