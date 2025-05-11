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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
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
    void deveSalvarAdvogadoERetornarResponse() {
        when(advogadoRepository.save(any(Advogado.class))).thenReturn(advogadoSalvo);

        AdvogadoResponse response = advogadoService.salvar(request);

        assertNotNull(response);
        assertEquals("Lucas Ronald", response.getNome());
        assertEquals("123456", response.getRegistroOab());
        assertEquals("lucas@email.com", response.getEmail());

        verify(advogadoRepository).save(any(Advogado.class));
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
        clienteRequest.setDataNascimento(LocalDate.of(1990, 1, 1));
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
}
