package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.response.ProcessoResponse;
import br.com.exemplo.crudusuariospring.model.Processo;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public ProcessoResponse salvar(ProcessoRequest processoRequest) {
        Processo processo = new Processo();
        processo.setNumeroProcesso(processoRequest.getNumeroProcesso());
        processo.setDescricao(processoRequest.getDescricao());
        processo.setStatus(processoRequest.getStatus());

        Processo processoSalvo = processoRepository.save(processo);

        ProcessoResponse processoResponse = new ProcessoResponse();
        processoResponse.setIdProcesso(processoSalvo.getIdProcesso());
        processoResponse.setNumeroProcesso(processoSalvo.getNumeroProcesso());
        processoResponse.setDescricao(processoSalvo.getDescricao());
        processoResponse.setStatus(processoSalvo.getStatus());

        return processoResponse;
    }

    public List<ProcessoResponse> listarTodos() {
        return processoRepository.findAll().stream().map(p -> {
            ProcessoResponse response = new ProcessoResponse();
            response.setIdProcesso(p.getIdProcesso());
            response.setNumeroProcesso(p.getNumeroProcesso());
            response.setDescricao(p.getDescricao());
            response.setStatus(p.getStatus());
            return response;
        }).collect(Collectors.toList());
    }

    public List<Processo> listarPorCliente(Integer idCliente) {
        return processoRepository.findByCliente_IdCliente(idCliente);
    }

}
