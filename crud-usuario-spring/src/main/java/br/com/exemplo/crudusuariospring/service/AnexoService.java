package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.AnexoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AnexoResponse;
import br.com.exemplo.crudusuariospring.model.Anexo;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.model.Processo;
import br.com.exemplo.crudusuariospring.observer.event.CadastroAnexoEvent;
import br.com.exemplo.crudusuariospring.observer.event.ClienteCadastradoEvent;
import br.com.exemplo.crudusuariospring.repository.AnexoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AnexoResponse criarAnexo(AnexoRequest request) {
        Anexo anexo = new Anexo();

        anexo.setIdAnexo(request.getIdAnexo());
        anexo.setNomeAnexo(request.getNomeAnexo());
        anexo.setIdItem(request.getIdItem());

        if (request.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            anexo.setCliente(cliente);
        }

        if (request.getIdProcesso() != null) {
            Processo processo = processoRepository.findById(request.getIdProcesso())
                    .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
            anexo.setProcesso(processo);
        }

        Anexo salvo = anexoRepository.save(anexo);
        eventPublisher.publishEvent(new CadastroAnexoEvent(this, salvo));
        return new AnexoResponse(salvo);
    }

    public List<AnexoResponse> buscarPorIdCliente(Integer idCliente) {
        List<Anexo> anexos = anexoRepository.findByCliente_IdCliente(idCliente);
        return anexos.stream().map(AnexoResponse::new).toList();
    }

    public List<AnexoResponse> buscarPorIdProcesso(Integer idProcesso) {
        List<Anexo> anexos = anexoRepository.findByProcesso_IdProcesso(idProcesso);
        return anexos.stream().map(AnexoResponse::new).toList();
    }

    @Transactional
    public void deletarAnexo(String idItem) {
        if (!anexoRepository.existsByIdItem(idItem)) {
            throw new RuntimeException("Anexo com ID " + idItem + " não encontrado.");
        }
        anexoRepository.deleteByIdItem(idItem);
    }
}
