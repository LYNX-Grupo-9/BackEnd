package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.NotificacaoRequest;
import br.com.exemplo.crudusuariospring.dto.response.NotificacaoResponse;
import br.com.exemplo.crudusuariospring.service.NotificacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    public NotificacaoResponse criarNotificacao(@RequestBody NotificacaoRequest notificacaoRequest) {
        return notificacaoService.criarNotificacao(notificacaoRequest);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public List<NotificacaoResponse> listarTodasNotificacoes() {
        return notificacaoService.listarTodasNotificacoes();
    }
}
