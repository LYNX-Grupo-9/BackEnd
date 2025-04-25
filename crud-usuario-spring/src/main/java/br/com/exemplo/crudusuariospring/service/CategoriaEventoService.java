package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.repository.CategoriaEventoRepository;
import br.com.exemplo.crudusuariospring.dto.response.CategoriaEventoResponse;
import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaEventoService {

    @Autowired
    private CategoriaEventoRepository categoriaEventoRepository;

    public CategoriaEventoResponse criarCategoria(CategoriaEventoResponse categoriaEventoResponse) {
        CategoriaEvento categoriaEvento = new CategoriaEvento();
        categoriaEvento.setNome(categoriaEventoResponse.getNomeEvento());
        categoriaEvento.setCor(categoriaEventoResponse.getCor());
        categoriaEvento = categoriaEventoRepository.save(categoriaEvento);
        return new CategoriaEventoResponse(categoriaEvento.getIdCategoria(), categoriaEvento.getNome(), categoriaEvento.getCor());
    }

    public List<CategoriaEventoResponse> buscarTodasCategorias() {
        List<CategoriaEvento> categorias = categoriaEventoRepository.findAll();
        return categorias.stream().map(categoria -> new CategoriaEventoResponse(categoria.getIdCategoria(), categoria.getNome(), categoria.getCor())).toList();
    }



}
