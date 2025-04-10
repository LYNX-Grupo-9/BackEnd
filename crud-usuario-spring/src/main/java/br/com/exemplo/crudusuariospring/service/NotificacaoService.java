package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.NotificacaoRequest;
import br.com.exemplo.crudusuariospring.dto.response.NotificacaoResponse;
import br.com.exemplo.crudusuariospring.model.Notificacao;
import br.com.exemplo.crudusuariospring.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public NotificacaoResponse criarNotificacao(NotificacaoRequest notificacaoRequest) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(notificacaoRequest.getTitulo());
        notificacao.setMensagem(notificacaoRequest.getMensagem());
        notificacao.setDataHora(notificacaoRequest.getDataHora());
        notificacao.setLida(notificacaoRequest.isLida());

        Notificacao notificacaoSalva = notificacaoRepository.save(notificacao);
        return new NotificacaoResponse(notificacaoSalva);
    }

    public List<NotificacaoResponse> listarTodasNotificacoes() {
        return notificacaoRepository.findAll()
                .stream()
                .map(NotificacaoResponse::new)
                .collect(Collectors.toList());
    }
}