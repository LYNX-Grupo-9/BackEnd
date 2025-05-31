package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarCategoriaRequest;
import br.com.exemplo.crudusuariospring.dto.request.CategoriaEventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.CategoriaEventoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.CategoriaEventoRepository;
import br.com.exemplo.crudusuariospring.repository.EventoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriaEventoServiceTest {

    @InjectMocks
    private CategoriaEventoService service;

    @Mock
    private CategoriaEventoRepository categoriaEventoRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private AdvogadoRepository advogadoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCategoriaComSucesso() {
        CategoriaEventoRequest request = new CategoriaEventoRequest();
        request.setNomeEvento("Reunião");
        request.setCor("Azul");
        request.setIdAdvogado(1);

        Advogado advogado = new Advogado();
        when(advogadoRepository.findById(1)).thenReturn(Optional.of(advogado));

        CategoriaEvento categoriaSalva = new CategoriaEvento();
        categoriaSalva.setIdCategoria(1L);
        categoriaSalva.setNome("Reunião");
        categoriaSalva.setCor("Azul");

        when(categoriaEventoRepository.save(any())).thenReturn(categoriaSalva);

        CategoriaEventoResponse response = service.criarCategoria(request);

        assertEquals(1L, response.getIdCategoriaEvento());
        assertEquals("Reunião", response.getNomeEvento());
        assertEquals("Azul", response.getCor());
    }

    @Test
    void deveBuscarTodasCategorias() {
        CategoriaEvento categoria1 = new CategoriaEvento();
        categoria1.setIdCategoria(1L);
        categoria1.setNome("Reunião");
        categoria1.setCor("Azul");

        CategoriaEvento categoria2 = new CategoriaEvento();
        categoria2.setIdCategoria(2L);
        categoria2.setNome("Audiência");
        categoria2.setCor("Vermelho");

        when(categoriaEventoRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        List<CategoriaEventoResponse> categorias = service.buscarTodasCategorias();

        assertEquals(2, categorias.size());
        assertEquals("Reunião", categorias.get(0).getNomeEvento());
        assertEquals("Audiência", categorias.get(1).getNomeEvento());
    }

    @Test
    void deveRetornarCategoriasPorAdvogado() {
        Integer idAdvogado = 1;

        CategoriaEvento categoria1 = new CategoriaEvento();
        categoria1.setIdCategoria(1L);
        categoria1.setNome("Reuniões");
        categoria1.setCor("#FF0000");

        CategoriaEvento categoria2 = new CategoriaEvento();
        categoria2.setIdCategoria(2L);
        categoria2.setNome("Audiências");
        categoria2.setCor("#00FF00");

        List<CategoriaEvento> categoriasMockadas = List.of(categoria1, categoria2);

        Mockito.when(categoriaEventoRepository.findByAdvogadoIdAdvogado(idAdvogado)).thenReturn(categoriasMockadas);

        List<CategoriaEventoResponse> resultado = service.CategoriaPorAdvogado(idAdvogado);

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Reuniões", resultado.get(0).getNomeEvento());
        Assertions.assertEquals("#FF0000", resultado.get(0).getCor());
        Assertions.assertEquals("Audiências", resultado.get(1).getNomeEvento());
        Assertions.assertEquals("#00FF00", resultado.get(1).getCor());

        Mockito.verify(categoriaEventoRepository).findByAdvogadoIdAdvogado(idAdvogado);
    }

    @Test
    void deveDeletarCategoria() {
        CategoriaEvento categoria = new CategoriaEvento();
        categoria.setIdCategoria(1L);

        when(categoriaEventoRepository.findById(1L)).thenReturn(Optional.of(categoria));

        service.deletarCategoria(1L);

        verify(eventoRepository).desvincularCategoriaDosEventos(1L);
        verify(categoriaEventoRepository).delete(categoria);
    }

    @Test
    void deveAtualizarCategoriaParcialmente() {
        CategoriaEvento categoria = new CategoriaEvento();
        categoria.setIdCategoria(1L);
        categoria.setNome("Antigo");
        categoria.setCor("Branco");

        AtualizarCategoriaRequest request = new AtualizarCategoriaRequest();
        request.setNomeEvento("Novo Nome");
        request.setCor("Preto");

        when(categoriaEventoRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaEventoRepository.save(any())).thenReturn(categoria);

        CategoriaEventoResponse response = service.atualizarParcialmente(1L, request);

        assertEquals("Novo Nome", response.getNomeEvento());
        assertEquals("Preto", response.getCor());
    }

    @Test
    public void deveRetornarCategoriaQuandoIdExiste() {
        Long id = 1L;
        CategoriaEvento categoria = new CategoriaEvento();
        categoria.setIdCategoria(id);
        categoria.setNome("Reunião");
        categoria.setCor("Azul");

        when(categoriaEventoRepository.findById(id)).thenReturn(Optional.of(categoria));

        CategoriaEventoResponse response = service.buscarCategoriaPorId(id);

        assertEquals(id, response.getIdCategoriaEvento());
        assertEquals("Reunião", response.getNomeEvento());
        assertEquals("Azul", response.getCor());
    }

    @Test
    public void deveLancarExcecaoQuandoCategoriaNaoExiste() {
        Long id = 99L;
        when(categoriaEventoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarCategoriaPorId(id);
        });

        assertEquals("Categoria não encontrada", exception.getMessage());
    }
}
