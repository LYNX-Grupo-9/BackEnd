package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.AdvogadoDetalhes;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AutenticacaoServiceTest {

    @Mock
    private AdvogadoRepository advogadoRepository;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUserDetailsQuandoEmailExiste() {
        Advogado advogado = new Advogado();
        advogado.setEmail("lucas@email.com");
        advogado.setSenha("senha123");

        when(advogadoRepository.findByEmail("lucas@email.com"))
                .thenReturn(Optional.of(advogado));

        UserDetails userDetails = autenticacaoService.loadUserByUsername("lucas@email.com");

        assertNotNull(userDetails);
        assertEquals("lucas@email.com", userDetails.getUsername());
        assertEquals("senha123", userDetails.getPassword());
        assertTrue(userDetails instanceof AdvogadoDetalhes);
    }

    @Test
    void deveLancarExcecaoQuandoEmailNaoExiste() {
        when(advogadoRepository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        UsernameNotFoundException excecao = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoService.loadUserByUsername("naoexiste@email.com")
        );

        assertEquals("Usuário naoexiste@email.com não encontrado", excecao.getMessage());
    }
}