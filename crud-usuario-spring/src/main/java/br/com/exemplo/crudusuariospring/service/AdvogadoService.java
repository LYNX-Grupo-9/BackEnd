package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.observer.CadastroAdvogadoSubject;
import br.com.exemplo.crudusuariospring.observer.EmailObserver;
import br.com.exemplo.crudusuariospring.observer.LogObserver;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import org.springframework.beans.factory.annotation.*;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdvogadoService {

    @Autowired
    private AdvogadoRepository repository;

    private CadastroAdvogadoSubject subject;

    public  AdvogadoService() {
        subject = new CadastroAdvogadoSubject();
        subject.adicionarObserver(new EmailObserver());
        subject.adicionarObserver(new LogObserver() );
    }

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
        Advogado advogado = toEntity(request);
        Advogado salvo = repository.save(advogado);

        String nomeAdvogado = salvo.getNome();
        subject.notificarTodos("Novo advogado cadastrado: " + nomeAdvogado);

        return toResponse(salvo);
    }

    public List<AdvogadoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


}
