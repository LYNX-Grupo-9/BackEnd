package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.model.Anexo;
import br.com.exemplo.crudusuariospring.repository.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    public Anexo salvarAnexo(Anexo anexo) {
        return anexoRepository.save(anexo);
    }

    public List<Anexo> pegarTodosAnexos() {
        return anexoRepository.findAll();
    }

}
