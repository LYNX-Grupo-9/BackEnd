package br.com.exemplo.crudusuariospring.service;


import br.com.exemplo.crudusuariospring.dto.request.AtualizarProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.request.ProcessoRequest;
import br.com.exemplo.crudusuariospring.dto.response.ProcessoResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.model.Processo;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import br.com.exemplo.crudusuariospring.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ProcessoResponse criarProcesso(ProcessoRequest request) {
        Processo processo = new Processo();

        processo.setTitulo(request.getTitulo());
        processo.setNumeroProcesso(request.getNumeroProcesso());
        processo.setDescricao(request.getDescricao());
        processo.setStatus(request.getStatus());
        processo.setClasseProcessual(request.getClasseProcessual());
        processo.setAssunto(request.getAssunto());
        processo.setTribunal(request.getTribunal());
        processo.setValor(request.getValor());
        processo.setAutor(request.getAutor());
        processo.setAdvRequerente(request.getAdvRequerente());
        processo.setReu(request.getReu());
        processo.setAdvReu(request.getAdvReu());

        Optional<Advogado> advogadoOpt = advogadoRepository.findById(request.getIdAdvogado());
        Optional<Cliente> clienteOpt = clienteRepository.findById(request.getIdCliente());

        advogadoOpt.ifPresent(processo::setAdvogado);
        clienteOpt.ifPresent(processo::setCliente);

        Processo processoSalvo = processoRepository.save(processo);

        return new ProcessoResponse(processoSalvo);
    }

    public List<ProcessoResponse> listarTodosPorIdAdvogado(Long idAdvogado) {
        List<Processo> processos = processoRepository.findByAdvogadoIdAdvogado(idAdvogado);

        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public ProcessoResponse buscarPorId(Integer idProcesso) {
        Optional<Processo> processoOpt = processoRepository.findById(idProcesso);

        if (processoOpt.isEmpty()) {
            throw new RuntimeException("Processo não encontrado com ID: " + idProcesso);
        }

        return new ProcessoResponse(processoOpt.get());
    }

    public List<ProcessoResponse> buscarPorIdCliente(Long idCliente) {
        List<Processo> processos = processoRepository.findByClienteIdCliente(idCliente);

        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public void excluirPorId(Integer idProcesso) {
        processoRepository.deleteById(idProcesso);
    }

    public ProcessoResponse atualizarProcessoParcialmente(Integer idProcesso, AtualizarProcessoRequest request) {
        Processo processo = processoRepository.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado para atualização"));

        if (request.getTitulo() != null) {
            processo.setTitulo(request.getTitulo());
        }
        if (request.getNumeroProcesso() != null) {
            processo.setNumeroProcesso(request.getNumeroProcesso());
        }
        if (request.getDescricao() != null) {
            processo.setDescricao(request.getDescricao());
        }
        if (request.getStatus() != null) {
            processo.setStatus(request.getStatus());
        }
        if (request.getClasseProcessual() != null) {
            processo.setClasseProcessual(request.getClasseProcessual());
        }
        if (request.getAssunto() != null) {
            processo.setAssunto(request.getAssunto());
        }
        if (request.getTribunal() != null) {
            processo.setTribunal(request.getTribunal());
        }
        if (request.getValor() != null) {
            processo.setValor(request.getValor());
        }
        if (request.getAutor() != null) {
            processo.setAutor(request.getAutor());
        }
        if (request.getAdvRequerente() != null) {
            processo.setAdvRequerente(request.getAdvRequerente());
        }
        if (request.getReu() != null) {
            processo.setReu(request.getReu());
        }
        if (request.getAdvReu() != null) {
            processo.setAdvReu(request.getAdvReu());
        }
        if (request.getIdAdvogado() != null) {
            advogadoRepository.findById(request.getIdAdvogado()).ifPresent(processo::setAdvogado);
        }
        if (request.getIdCliente() != null) {
            clienteRepository.findById(request.getIdCliente()).ifPresent(processo::setCliente);
        }

        Processo atualizado = processoRepository.save(processo);

        return new ProcessoResponse(atualizado);
    }

    public List<ProcessoResponse> listarProcessosAtivosPorAdvogado(Long idAdvogado) {
        List<Processo> processos = processoRepository.findByStatusIgnoreCaseAndAdvogadoIdAdvogado("Ativo", idAdvogado);
        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public Map<String, Long> contarProcessosPorStatusPorAdvogado(Long idAdvogado) {
        List<Processo> processos = processoRepository.findByAdvogadoIdAdvogado(idAdvogado);

        return processos.stream()
                .collect(Collectors.groupingBy(
                        Processo::getStatus,
                        Collectors.counting()
                ));
    }

    public Map<String, Long> contarProcessosPorClasseProcessualPorAdvogado(Long idAdvogado) {
        List<Object[]> resultados = processoRepository.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);

        return resultados.stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (Long) r[1]
                ));
    }

    public List<ProcessoResponse> listarProcessosOrdenadosPorNomeCliente(Long idAdvogado) {
        List<Processo> processos = processoRepository.buscarOrdenadoPorCliente(idAdvogado);
        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProcessoResponse> listarProcessosOrdenadosPorNumeroDeProcesso(Long idAdvogado) {
        List<Processo> processos = processoRepository.findByAdvogadoIdAdvogadoOrderByNumeroProcessoAsc(idAdvogado);
        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProcessoResponse> listarProcessosOrdenadosPorValor(Long idAdvogado) {
        List<Processo> processos = processoRepository.findByAdvogadoIdAdvogadoOrderByValorAsc(idAdvogado);
        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProcessoResponse> buscarPorTexto(String termo, Long idAdvogado) {
        List<Processo> processos = processoRepository.buscarPorNumeroTituloOuCliente(termo, idAdvogado);
        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProcessoResponse> listarProcessosOrdenadosPorStatus(Long idAdvogado) {
        List<Processo> processos = processoRepository.findByAdvogadoIdAdvogadoOrderByStatusAsc(idAdvogado);
        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }

}
