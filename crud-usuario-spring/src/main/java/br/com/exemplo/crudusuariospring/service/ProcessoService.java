package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.AdvogadoMapper;
import br.com.exemplo.crudusuariospring.dto.request.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;
import br.com.exemplo.crudusuariospring.dto.response.ProcessoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Processo;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        ProcessoResponse processoResponse = new ProcessoResponse(processoSalvo);

        return processoResponse;
    }

    public List<ProcessoResponse> listarTodos() {
        return processoRepository.findAll().stream()
                .map(p -> new ProcessoResponse(p))
                .collect(Collectors.toList());
    }


    public List<Processo> listarPorCliente(Integer idCliente) {
        return processoRepository.findByCliente_IdCliente(idCliente);
    }

    public ProcessoResponse buscarPorId(Integer id) {
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));

        return new ProcessoResponse(processo);
    }

}
