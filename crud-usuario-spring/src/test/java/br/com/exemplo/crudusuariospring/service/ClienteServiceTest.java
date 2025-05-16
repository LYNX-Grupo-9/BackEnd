package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.ClienteProcessoEventoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.*;
import br.com.exemplo.crudusuariospring.observer.CadastroClienteSubject;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.Subject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private CadastroClienteSubject subject;

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
        cliente.setDataNascimento(new Date(2000, 2, 1));
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
        Integer idAdvogado = 1;
        when(clienteRepository.findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(idAdvogado)).thenReturn(Arrays.asList(cliente3, cliente2, cliente1));

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorNaturalidade(idAdvogado);

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Bruna Costa", resposta.get(0).getNome());
        assertEquals("Curitiba", resposta.get(0).getNaturalidade());
        assertEquals("Ana Oliveira", resposta.get(1).getNome());
        assertEquals("Rio de Janeiro", resposta.get(1).getNaturalidade());

        verify(clienteRepository).findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(idAdvogado);
    }

    @Test
    void testListarOrdenadoPorDataNascimento() {
        Integer idAdvogado = 1;
        cliente1.setDataNascimento(new Date(1990, 1, 1));
        cliente2.setDataNascimento(new Date(1985, 6, 15));
        cliente3.setDataNascimento(new Date(2000, 12, 31));

        when(clienteRepository.findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(idAdvogado)).thenReturn(Arrays.asList(cliente2, cliente1, cliente3));

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorDataNascimento(idAdvogado);

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Ana Oliveira", resposta.get(0).getNome());
        assertEquals("Carlos Silva", resposta.get(1).getNome());
        assertEquals("Bruna Costa", resposta.get(2).getNome());

        verify(clienteRepository).findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(idAdvogado);
    }

    @Test
    void testListarOrdenadoPorQuantidadeProcessos() {
        Integer idAdvogado = 1;
        when(clienteRepository.ordenarPorQuantidadeProcessos(idAdvogado)).thenReturn(Arrays.asList(cliente2, cliente1, cliente3));

        when(processoRepository.countByCliente_IdCliente(cliente1.getIdCliente())).thenReturn(2);
        when(processoRepository.countByCliente_IdCliente(cliente2.getIdCliente())).thenReturn(5);
        when(processoRepository.countByCliente_IdCliente(cliente3.getIdCliente())).thenReturn(1);

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorQuantidadeProcessos(idAdvogado);

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Ana Oliveira", resposta.get(0).getNome());
        assertEquals("Carlos Silva", resposta.get(1).getNome());
        assertEquals("Bruna Costa", resposta.get(2).getNome());
    }


    @Test
    void testBuscarClientePorTexto() {
        Integer idAdvogado = 1;
        String texto = "lucas";
        when(clienteRepository.buscarPorNomeEmailTelefonePorAdvogado(texto, idAdvogado))
                .thenReturn(List.of(cliente));

        List<ClienteResponse> resultado = clienteService.buscarPorTexto(texto, idAdvogado);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas Ronald", resultado.get(0).getNome());

        verify(clienteRepository).buscarPorNomeEmailTelefonePorAdvogado(texto, idAdvogado);
    }

    @Test
    void testBuscarDadosCliente_Sucesso() {
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.MAY, 20, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dataReuniao = cal.getTime();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNome("Lucas Ronald");
        cliente.setDocumento("123456789");
        cliente.setTipoDocumento("CPF");
        cliente.setEmail("lucas@example.com");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua A, 123");
        cliente.setGenero("Masculino");
        cliente.setDataNascimento(new Date(1990, 1, 1));
        cliente.setEstadoCivil("Solteiro");
        cliente.setProfissao("Programador");
        cliente.setPassaporte("XYZ12345");
        cliente.setCnh("CNH123456");
        cliente.setNaturalidade("São Paulo");

        Processo processo = new Processo();
        processo.setNumeroProcesso("0001234-56.2025.8.26.0100");
        cliente.setProcessos(List.of(processo));

        CategoriaEvento categoria = new CategoriaEvento();
        categoria.setNome("Reunião");

        Evento evento = new Evento();
        evento.setIdEvento(10L);
        evento.setDataReuniao(dataReuniao);
        evento.setHoraInicio(LocalTime.of(14, 0));
        evento.setHoraFim(LocalTime.of(15, 0));
        evento.setNome("Reunião com cliente");
        evento.setCategoria(categoria);

        cliente.setEventos(List.of(evento));

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteProcessoEventoResponse response = clienteService.buscarDadosCliente(1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataEsperada = "2025-05-20";
        String dataObtida = sdf.format(response.getEventos().get(0).getDataEvento());

        assertNotNull(response);
        assertEquals(1, response.getIdCliente());
        assertEquals("Lucas Ronald", response.getNome());
        assertEquals("123456789", response.getDocumento());
        assertEquals("CPF", response.getTipoDocumento());
        assertEquals("lucas@example.com", response.getEmail());
        assertEquals("11999999999", response.getTelefone());
        assertEquals("Rua A, 123", response.getEndereco());
        assertEquals("Masculino", response.getGenero());
        assertEquals(new Date(1990, 1, 1), response.getDataNascimento());
        assertEquals("Solteiro", response.getEstadoCivil());
        assertEquals("Programador", response.getProfissao());
        assertEquals("XYZ12345", response.getPassaporte());
        assertEquals("CNH123456", response.getCnh());
        assertEquals("São Paulo", response.getNaturalidade());

        assertNotNull(response.getProcessos());
        assertEquals(1, response.getProcessos().size());
        assertEquals("0001234-56.2025.8.26.0100", response.getProcessos().get(0).getNumeroProcesso());

        assertNotNull(response.getEventos());
        assertEquals(1, response.getEventos().size());
        ClienteProcessoEventoResponse.EventoResponse eventoResponse = response.getEventos().get(0);
        assertEquals(10, eventoResponse.getIdEvento());
        assertEquals(dataEsperada, dataObtida);
        assertEquals(LocalTime.of(14, 0), eventoResponse.getHoraInicio());
        assertEquals(LocalTime.of(15, 0), eventoResponse.getHoraFim());
        assertEquals("Reunião com cliente", eventoResponse.getTitulo());
        assertEquals("Reunião", eventoResponse.getTipo());
    }

    @Test
    void testBuscarDadosCliente_ClienteNaoEncontrado() {
        when(clienteRepository.findById(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.buscarDadosCliente(999);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    void deveSalvarClienteQuandoDadosValidos() {
        ClienteRequest request = new ClienteRequest();
        request.setNome("Lucas Ronald");
        request.setDocumento("12345678900");
        request.setEmail("lucas@email.com");
        request.setTelefone("11999999999");
        request.setEndereco("Rua Teste");
        request.setEstadoCivil("Solteiro");
        request.setGenero("Masculino");
        request.setProfissao("Desenvolvedor");
        request.setPassaporte("X1234567");
        request.setCnh("CNH12345");
        request.setNaturalidade("São Paulo");
        request.setDataNascimento(new Date(1990, 1, 1));
        request.setIdAdvogado(1);

        when(advogadoRepository.findById(1)).thenReturn(Optional.of(advogado));
        when(clienteRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente clienteSalvo = invocation.getArgument(0);
            clienteSalvo.setIdCliente(100);
            return clienteSalvo;
        });

        doNothing().when(subject).notificarTodos(anyString());

        ClienteResponse response = clienteService.salvar(request);

        assertNotNull(response);
        assertEquals(100, response.getIdCliente());
        assertEquals(request.getNome(), response.getNome());
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getTelefone(), response.getTelefone());
        assertEquals(advogado.getNome(), response.getAdvogadoResponsavel());

        verify(advogadoRepository).findById(1);
        verify(clienteRepository).existsByEmail(request.getEmail());
        verify(clienteRepository).save(any(Cliente.class));
        verify(subject).notificarTodos("Novo cliente cadastrado: " + request.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoAdvogadoNaoEncontrado() {
        ClienteRequest request = new ClienteRequest();
        request.setIdAdvogado(99);

        when(advogadoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.salvar(request);
        });

        assertEquals("Advogado não encontrado.", exception.getMessage());

        verify(advogadoRepository).findById(99);
        verify(clienteRepository, times(0)).save(any());
        verify(subject, times(0)).notificarTodos(anyString());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        ClienteRequest request = new ClienteRequest();
        request.setEmail("email@existente.com");
        request.setIdAdvogado(1);

        when(advogadoRepository.findById(1)).thenReturn(Optional.of(advogado));
        when(clienteRepository.existsByEmail(request.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.salvar(request);
        });

        assertEquals("Cliente com este e-mail já está cadastrado.", exception.getMessage());

        verify(advogadoRepository).findById(1);
        verify(clienteRepository).existsByEmail(request.getEmail());
        verify(clienteRepository, times(0)).save(any());
        verify(subject, times(0)).notificarTodos(anyString());
    }

    @Test
    void deveBuscarClienteComQuantidadeProcessosQuandoClienteExiste() {
        Integer idCliente = cliente.getIdCliente();
        Integer qtdProcessos = 3;

        when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(cliente));
        when(processoRepository.countByCliente_IdCliente(idCliente)).thenReturn(qtdProcessos);

        ClienteResponse response = clienteService.buscarClienteComQuantidadeProcessos(idCliente);

        assertNotNull(response);
        assertEquals(cliente.getIdCliente(), response.getIdCliente());
        assertEquals(cliente.getNome(), response.getNome());
        assertEquals(cliente.getEmail(), response.getEmail());
        assertEquals(qtdProcessos, response.getQtdProcessos());

        String advogadoEsperado = cliente.getAdvogado() != null ? cliente.getAdvogado().getNome() : "Não atribuído";
        assertEquals(advogadoEsperado, response.getAdvogadoResponsavel());

        verify(clienteRepository).findById(idCliente);
        verify(processoRepository).countByCliente_IdCliente(idCliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Integer idCliente = 999;

        when(clienteRepository.findById(idCliente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.buscarClienteComQuantidadeProcessos(idCliente);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());

        verify(clienteRepository).findById(idCliente);
        verify(processoRepository, times(0)).countByCliente_IdCliente(any());
    }

    @Test
    void testListarOrdenadoPorNome() {
        Integer idAdvogado = 1;
        when(clienteRepository.findByAdvogadoIdAdvogadoOrderByNomeAsc(idAdvogado)).thenReturn(Arrays.asList(cliente1, cliente2, cliente3));

        List<ClienteResponse> resposta = clienteService.listarOrdenadoPorNome(idAdvogado);

        assertNotNull(resposta);
        assertEquals(3, resposta.size());
        assertEquals("Carlos Silva", resposta.get(0).getNome());
        assertEquals("Ana Oliveira", resposta.get(1).getNome());
        assertEquals("Bruna Costa", resposta.get(2).getNome());

        verify(clienteRepository).findByAdvogadoIdAdvogadoOrderByNomeAsc(idAdvogado);
    }

    @Test
    public void listarPorAdvogado_deveRetornarListaDeClienteResponse() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = sdf.parse("1990-01-01");

        Advogado advogado = new Advogado();
        advogado.setNome("João Silva");

        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(1);
        cliente1.setNome("Cliente A");
        cliente1.setEmail("clientea@email.com");
        cliente1.setDocumento("123456789");
        cliente1.setTipoDocumento("RG");
        cliente1.setTelefone("99999-9999");
        cliente1.setEndereco("Rua A, 123");
        cliente1.setEstadoCivil("Solteiro");
        cliente1.setGenero("Masculino");
        cliente1.setProfissao("Engenheiro");
        cliente1.setPassaporte("AB12345");
        cliente1.setCnh("XYZ1234");
        cliente1.setNaturalidade("Cidade X");
        cliente1.setDataNascimento(dataNascimento);
        cliente1.setQtdProcessos(2);
        cliente1.setAdvogado(advogado);

        when(clienteRepository.findByAdvogadoIdAdvogado(1)).thenReturn(Arrays.asList(cliente1));

        List<ClienteResponse> lista = clienteService.listarPorAdvogado(1);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        ClienteResponse resp = lista.get(0);
        assertEquals(cliente1.getIdCliente(), resp.getIdCliente());
        assertEquals(cliente1.getNome(), resp.getNome());
        assertEquals(cliente1.getEmail(), resp.getEmail());
        assertEquals(cliente1.getDocumento(), resp.getDocumento());
        assertEquals(cliente1.getTipoDocumento(), resp.getTipoDocumento());
        assertEquals(cliente1.getTelefone(), resp.getTelefone());
        assertEquals(cliente1.getEndereco(), resp.getEndereco());
        assertEquals(cliente1.getEstadoCivil(), resp.getEstadoCivil());
        assertEquals(cliente1.getGenero(), resp.getGenero());
        assertEquals(cliente1.getProfissao(), resp.getProfissao());
        assertEquals(cliente1.getPassaporte(), resp.getPassaporte());
        assertEquals(cliente1.getCnh(), resp.getCnh());
        assertEquals(cliente1.getNaturalidade(), resp.getNaturalidade());
        assertEquals(cliente1.getDataNascimento(), resp.getDataNascimento());
        assertEquals(cliente1.getQtdProcessos(), resp.getQtdProcessos());
        assertEquals(advogado.getNome(), resp.getAdvogadoResponsavel());
    }
}
