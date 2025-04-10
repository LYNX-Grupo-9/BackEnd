package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.ReuniaoRequest;
import br.com.exemplo.crudusuariospring.dto.response.ReuniaoResponse;
import br.com.exemplo.crudusuariospring.model.Reuniao;
import br.com.exemplo.crudusuariospring.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    public ReuniaoResponse salvarReuniao(ReuniaoRequest request) {
        Reuniao reuniao = new Reuniao(
                request.getDescricaoReuniao(),
                request.getLocal(),
                request.getDataHoraInicio(),
                request.getDataHoraFim()
        );
        reuniaoRepository.save(reuniao);
        return toResponse(reuniao);
    }

    public List<ReuniaoResponse> listarReunioes() {
        return reuniaoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ReuniaoResponse toResponse(Reuniao reuniao) {
        return new ReuniaoResponse(
                reuniao.getIdReuniao(),
                reuniao.getDescricaoReuniao(),
                reuniao.getLocal(),
                reuniao.getDataHoraInicio(),
                reuniao.getDataHoraFim()
        );
    }


}
