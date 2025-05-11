package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteProcessoEventoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente, cliente1, cliente2, cliente3;
    private Advogado advogado;

    @BeforeEach
    void setup() {
        advogado = criarAdvogado();

        cliente = criarCliente(1, "Lucas Ronald", "lucas@email.com", "São Paulo");
        cliente1 = criarCliente(2, "Carlos Silva", "carlos@email.com", "São Paulo");
        cliente2 = criarCliente(3, "Ana Oliveira", "ana@email.com", "Rio de Janeiro");
        cliente3 = criarCliente(4, "Bruna Costa", "bruna@email.com", "Curitiba");
    }

    private Cliente criarCliente(Integer IdCliente, String nome, String email, String naturalidade) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(IdCliente);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua Teste");
        cliente.setEstadoCivil("Solteiro");
        cliente.setGenero("Feminino");
        cliente.setProfissao("Desenvolvedor");
        cliente.setDataNascimento(LocalDate.of(2000, 2, 1));
        cliente.setNaturalidade(naturalidade);
        cliente.setAdvogado(advogado);
        return cliente;
    }

    private Advogado criarAdvogado() {
        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);
        advogado.setNome("Dr. João");
        return advogado;
    }

    @Test
    void testListarTodosClientes() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente3, cliente2, cliente1, cliente));

        List<ClienteResponse> response = clienteService.listarTodos();

        assertNotNull(response);
        assertEquals(4, response.size());
    }

    @Test
    void testListarOrdenadoPorNaturalidade() {
        when(clienteRepository.findAllByOrderByNaturalidadeAsc()).thenReturn(Arrays.asList(cliente3, cliente2, cliente1));

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorNaturalidade();

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Bruna Costa", resposta.get(0).getNome());
        assertEquals("Curitiba", resposta.get(0).getNaturalidade());
        assertEquals("Ana Oliveira", resposta.get(1).getNome());
        assertEquals("Rio de Janeiro", resposta.get(1).getNaturalidade());

        verify(clienteRepository).findAllByOrderByNaturalidadeAsc();
    }

    @Test
    void testListarOrdenadoPorDataNascimento() {
        cliente1.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente2.setDataNascimento(LocalDate.of(1985, 6, 15));
        cliente3.setDataNascimento(LocalDate.of(2000, 12, 31));

        when(clienteRepository.findAllByOrderByDataNascimentoAsc()).thenReturn(Arrays.asList(cliente2, cliente1, cliente3));

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorDataNascimento();

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Ana Oliveira", resposta.get(0).getNome());
        assertEquals("Carlos Silva", resposta.get(1).getNome());
        assertEquals("Bruna Costa", resposta.get(2).getNome());

        verify(clienteRepository).findAllByOrderByDataNascimentoAsc();
    }

    @Test
    void testListarOrdenadoPorQuantidadeProcessos() {
        when(clienteRepository.ordenarPorQuantidadeProcessos()).thenReturn(Arrays.asList(cliente2, cliente1, cliente3));

        when(processoRepository.countByCliente_IdCliente(cliente1.getIdCliente())).thenReturn(2);
        when(processoRepository.countByCliente_IdCliente(cliente2.getIdCliente())).thenReturn(5);
        when(processoRepository.countByCliente_IdCliente(cliente3.getIdCliente())).thenReturn(1);

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorQuantidadeProcessos();

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Ana Oliveira", resposta.get(0).getNome());
        assertEquals("Carlos Silva", resposta.get(1).getNome());
        assertEquals("Bruna Costa", resposta.get(2).getNome());
    }


    @Test
    void testBuscarClientePorTexto() {
        String texto = "lucas";
        when(clienteRepository.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefoneContaining(texto, texto, texto))
                .thenReturn(List.of(cliente));

        List<ClienteResponse> resultado = clienteService.buscarPorTexto(texto);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas Ronald", resultado.get(0).getNome());

        verify(clienteRepository).findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefoneContaining(texto, texto, texto);
    }

    @Test
    void buscarDadosCliente() {
        cliente.setProcessos(new ArrayList<>());
        cliente.setEventos(new ArrayList<>());

        when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));

        ClienteProcessoEventoResponse response = clienteService.buscarDadosCliente(cliente.getIdCliente());

        assertNotNull(response);
        assertEquals(cliente.getNome(), response.getNome());
        assertEquals(cliente.getEmail(), response.getEmail());
    }
}
