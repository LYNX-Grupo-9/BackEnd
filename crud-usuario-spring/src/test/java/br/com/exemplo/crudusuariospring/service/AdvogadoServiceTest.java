package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoToken;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.config.GerenciadorTokenJwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdvogadoServiceTest {

    @Mock
    private AdvogadoRepository advogadoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AdvogadoService advogadoService;

    private AdvogadoRequest request;
    private Advogado advogadoSalvo;

    @BeforeEach
    void setUp() {
        request = new AdvogadoRequest();
        request.setNome("Lucas Ronald");
        request.setRegistroOab("123456");
        request.setCpf("11122233344");
        request.setEmail("lucas@email.com");
        request.setSenha("senha123");

        advogadoSalvo = new Advogado();
        advogadoSalvo.setIdAdvogado(1);
        advogadoSalvo.setNome("Lucas Ronald");
        advogadoSalvo.setRegistroOab("123456");
        advogadoSalvo.setCpf("11122233344");
        advogadoSalvo.setEmail("lucas@email.com");
        advogadoSalvo.setSenha("senha123");
    }

    @Test
    void deveAutenticarAdvogadoERetornarToken() {
        Advogado advogado = new Advogado();
        advogado.setEmail("lucas@email.com");
        advogado.setSenha("senha123");

        Authentication authMock = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authMock);

        when(gerenciadorTokenJwt.gerarToken(authMock)).thenReturn("jwt-token");

        when(advogadoRepository.findByEmail("lucas@email.com")).thenReturn(java.util.Optional.of(advogado));

        AdvogadoToken token = advogadoService.autenticar(advogado);

        assertNotNull(token);
        assertEquals("lucas@email.com", token.getEmail());
        assertEquals("jwt-token", token.getToken());

        verify(authenticationManager).authenticate(any());
        verify(gerenciadorTokenJwt).gerarToken(authMock);
        verify(advogadoRepository).findByEmail("lucas@email.com");
    }

    @Test
    void deveCadastrarClienteComAdvogadoAutenticado() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("advogado@email.com");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(mock(Authentication.class));
        when(securityContext.getAuthentication().getPrincipal()).thenReturn(userDetails);

        SecurityContextHolder.setContext(securityContext);

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);
        advogado.setNome("Dr. Lucas");
        advogado.setEmail("advogado@email.com");

        when(advogadoRepository.findByEmail("advogado@email.com"))
                .thenReturn(Optional.of(advogado));

        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNome("Cliente Teste");
        clienteRequest.setEmail("cliente@email.com");
        clienteRequest.setDocumento("12345678900");
        clienteRequest.setTipoDocumento("CPF");
        clienteRequest.setTelefone("11999999999");
        clienteRequest.setEndereco("Rua A, 123");
        clienteRequest.setGenero("Masculino");
        clienteRequest.setDataNascimento(new Date(1990, 1, 1));
        clienteRequest.setEstadoCivil("Solteiro");
        clienteRequest.setProfissao("Engenheiro");
        clienteRequest.setPassaporte("P12345");
        clienteRequest.setCnh("CNH12345");
        clienteRequest.setNaturalidade("São Paulo");

        when(clienteRepository.existsByEmail("cliente@email.com")).thenReturn(false);
        when(clienteRepository.existsByDocumento("12345678900")).thenReturn(false);

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setIdCliente(1);
        clienteSalvo.setNome(clienteRequest.getNome());
        clienteSalvo.setEmail(clienteRequest.getEmail());
        clienteSalvo.setTelefone(clienteRequest.getTelefone());
        clienteSalvo.setEndereco(clienteRequest.getEndereco());
        clienteSalvo.setGenero(clienteRequest.getGenero());
        clienteSalvo.setDataNascimento(clienteRequest.getDataNascimento());
        clienteSalvo.setEstadoCivil(clienteRequest.getEstadoCivil());
        clienteSalvo.setProfissao(clienteRequest.getProfissao());
        clienteSalvo.setPassaporte(clienteRequest.getPassaporte());
        clienteSalvo.setCnh(clienteRequest.getCnh());
        clienteSalvo.setNaturalidade(clienteRequest.getNaturalidade());
        clienteSalvo.setAdvogado(advogado);

        when(clienteRepository.save(any())).thenReturn(clienteSalvo);

        ClienteResponse response = advogadoService.cadastrarCliente(clienteRequest);

        assertNotNull(response);
        assertEquals("Cliente Teste", response.getNome());
        assertEquals("cliente@email.com", response.getEmail());
        assertEquals("Dr. Lucas", response.getAdvogadoResponsavel());

        verify(clienteRepository).save(any());
    }

    @Test
    void deveBuscarAdvogadoPorEmailERetornarResponse() {
        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(1);
        advogado.setNome("Lucas Ronald");
        advogado.setRegistroOab("123456");
        advogado.setCpf("11122233344");
        advogado.setEmail("lucas@email.com");
        advogado.setSenha("senha123");

        when(advogadoRepository.findByEmail("lucas@email.com"))
                .thenReturn(Optional.of(advogado));

        AdvogadoResponse response = advogadoService.buscarPorEmail("lucas@email.com");

        assertNotNull(response);
        assertEquals("Lucas Ronald", response.getNome());
        assertEquals("123456", response.getRegistroOab());
        assertEquals("lucas@email.com", response.getEmail());

        verify(advogadoRepository).findByEmail("lucas@email.com");
    }

    @Test
    void deveLancarExcecaoQuandoAdvogadoNaoEncontradoPorEmail() {
        when(advogadoRepository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            advogadoService.buscarPorEmail("naoexiste@email.com");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Advogado não encontrado", exception.getReason());

        verify(advogadoRepository).findByEmail("naoexiste@email.com");
    }

    @Test
    void deveListarTodosOsAdvogadosERetornarListaDeResponses() {
        Advogado advogado1 = new Advogado();
        advogado1.setIdAdvogado(1);
        advogado1.setNome("Lucas Ronald");
        advogado1.setRegistroOab("123456");
        advogado1.setCpf("11122233344");
        advogado1.setEmail("lucas@email.com");
        advogado1.setSenha("senha123");

        Advogado advogado2 = new Advogado();
        advogado2.setIdAdvogado(2);
        advogado2.setNome("Joana Alves");
        advogado2.setRegistroOab("654321");
        advogado2.setCpf("99988877766");
        advogado2.setEmail("joana@email.com");
        advogado2.setSenha("senha456");

        List<Advogado> advogados = List.of(advogado1, advogado2);

        when(advogadoRepository.findAll()).thenReturn(advogados);

        List<AdvogadoResponse> responses = advogadoService.listarTodosAdvogados();

        assertNotNull(responses);
        assertEquals(2, responses.size());

        assertEquals("Lucas Ronald", responses.get(0).getNome());
        assertEquals("Joana Alves", responses.get(1).getNome());

        verify(advogadoRepository).findAll();
    }

    @Test
    void deveCriarAdvogadoComSenhaCriptografadaESalvarNoRepositorio() {
        Advogado novoAdvogado = new Advogado();
        novoAdvogado.setNome("Lucas Ronald");
        novoAdvogado.setRegistroOab("123456");
        novoAdvogado.setCpf("11122233344");
        novoAdvogado.setEmail("lucas@email.com");
        novoAdvogado.setSenha("senha123");

        String senhaCriptografada = "$2a$10$abc123senhaCriptografada";

        when(passwordEncoder.encode("senha123")).thenReturn(senhaCriptografada);

        advogadoService.criarAdvogado(novoAdvogado);

        assertEquals(senhaCriptografada, novoAdvogado.getSenha());

        verify(passwordEncoder).encode("senha123");
        verify(advogadoRepository).save(novoAdvogado);
    }

}
