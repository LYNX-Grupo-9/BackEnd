package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Evento;

import java.time.LocalDateTime;

public class EventoResponse {

    private Long idEvento;
    private String tipo;
    private String descricao;
    private LocalDateTime dataHora;
    private String local;
    private String nomeCliente;

    public EventoResponse(Evento evento){
        this.idEvento = evento.getIdEvento();
        this.tipo = evento.getTipo();
        this.descricao = evento.getDescricao();
        this.dataHora = evento.getDataHora();
        this.local = evento.getLocal();

        if (evento.getCliente() != null) {
            this.nomeCliente = evento.getCliente().getNome();
        }
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
