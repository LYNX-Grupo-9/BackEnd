package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.exception.RecursoJaExisteException;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import org.springframework.beans.factory.annotation.*;
import br.com.exemplo.crudusuariospring.dto.AdvogadoResponse;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdvogadoService {

    @Autowired
    private AdvogadoRepository repository;

    private Advogado toEntity(AdvogadoRequest dto) {
        Advogado advogado = new Advogado();
        advogado.setNome(dto.getNome());
        advogado.setRegistroOab(dto.getRegistroOab());
        advogado.setCpf(dto.getCpf());
        advogado.setEmail(dto.getEmail());
        advogado.setSenha(dto.getSenha());
        return advogado;
    }

    private AdvogadoResponse toResponse(Advogado advogado) {
        AdvogadoResponse response = new AdvogadoResponse();
        response.setIdAdvogado(advogado.getIdAdvogado());
        response.setNome(advogado.getNome());
        response.setEmail(advogado.getEmail());
        response.setRegistroOab(advogado.getRegistroOab());
        return response;
    }

    public AdvogadoResponse salvar(AdvogadoRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RecursoJaExisteException("Já existe um advogado cadastrado com este e-mail.");
        }

        Advogado advogado = toEntity(request);
        Advogado salvo = repository.save(advogado);
        return toResponse(salvo);
    }

    public List<AdvogadoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


}
