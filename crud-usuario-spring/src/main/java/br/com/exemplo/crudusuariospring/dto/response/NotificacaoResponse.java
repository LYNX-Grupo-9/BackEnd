package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Notificacao;

import java.time.LocalDateTime;

public class NotificacaoResponse {

    private Long idNotificacao;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataHora;
    private boolean lida;

    public NotificacaoResponse(Notificacao notificacao) {
        this.idNotificacao = notificacao.getIdNotificacao();
        this.titulo = notificacao.getTitulo();
        this.mensagem = notificacao.getMensagem();
        this.dataHora = notificacao.getDataHora();
        this.lida = notificacao.isLida();
    }

    public Long getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Long idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }
}
