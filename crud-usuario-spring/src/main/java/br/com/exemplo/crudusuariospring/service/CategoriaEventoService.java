package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AtualizarCategoriaRequest;
import br.com.exemplo.crudusuariospring.dto.request.CategoriaEventoRequest;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.CategoriaEventoRepository;
import br.com.exemplo.crudusuariospring.dto.response.CategoriaEventoResponse;
import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import br.com.exemplo.crudusuariospring.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaEventoService {

    @Autowired
    private CategoriaEventoRepository categoriaEventoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    public CategoriaEventoResponse criarCategoria(CategoriaEventoRequest request) {
        CategoriaEvento categoriaEvento = new CategoriaEvento();
        categoriaEvento.setNome(request.getNomeEvento());
        categoriaEvento.setCor(request.getCor());

        if (request.getIdAdvogado() != null) {
            Advogado advogado = advogadoRepository.findById(request.getIdAdvogado())
                    .orElseThrow(() -> new RuntimeException("Advogado não encontrado com ID: " + request.getIdAdvogado()));
            categoriaEvento.setAdvogado(advogado);
        }

        categoriaEvento = categoriaEventoRepository.save(categoriaEvento);

        return new CategoriaEventoResponse(
                categoriaEvento.getIdCategoria(),
                categoriaEvento.getNome(),
                categoriaEvento.getCor()
        );
    }


    public List<CategoriaEventoResponse> buscarTodasCategorias() {
        List<CategoriaEvento> categorias = categoriaEventoRepository.findAll();
        return categorias.stream().map(categoria -> new CategoriaEventoResponse(categoria.getIdCategoria(), categoria.getNome(), categoria.getCor())).toList();
    }

    public List<CategoriaEventoResponse> CategoriaPorAdvogado(Integer idAdvogado) {
        List<CategoriaEvento> categorias = categoriaEventoRepository.findByAdvogadoIdAdvogado(idAdvogado);
        return categorias.stream()
                .map(c -> new CategoriaEventoResponse(c.getIdCategoria(), c.getNome(), c.getCor()))
                .toList();
    }

    public CategoriaEventoResponse buscarCategoriaPorId(Long idCategoria) {
        CategoriaEvento categoria = categoriaEventoRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaEventoResponse(categoria.getIdCategoria(), categoria.getNome(), categoria.getCor());
    }

    public void deletarCategoria(Long id) {
        CategoriaEvento categoria = categoriaEventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));

        eventoRepository.desvincularCategoriaDosEventos(id);
        categoriaEventoRepository.delete(categoria);
    }


    public CategoriaEventoResponse atualizarParcialmente(Long id, AtualizarCategoriaRequest request) {
        CategoriaEvento categoria = categoriaEventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if(request.getNomeEvento() != null){
            categoria.setNome(request.getNomeEvento());
        }
        if (request.getCor() != null){
            categoria.setCor(request.getCor());
        }

        CategoriaEvento categoriaAtualizado = categoriaEventoRepository.save(categoria);
        return new CategoriaEventoResponse(
                categoriaAtualizado.getIdCategoria(),
                categoriaAtualizado.getNome(),
                categoriaAtualizado.getCor()
        );
    }

    public Map<String, Long> contarCategoriaPorNome() {
        List<Object[]> resultados = categoriaEventoRepository.contarCategoriasAgrupadasPorNome();

        Map<String, Long> mapa = new HashMap<>();

        for (Object[] linha : resultados) {
            String nome = (String) linha[0];
            Long quantidade = (Long) linha[1];
            mapa.put(nome, quantidade);
        }

        return mapa;
    }
}
