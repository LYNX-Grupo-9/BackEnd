package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Evento;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventoResponse {

    private Long idEvento;
    private String nome;
    private String descricao;
    private String local;
    private String linkReuniao;
    private String nomeAdvogado;
    private String nomeCliente;
    private String nomeCategoria;
    private String numeroProcesso;
    private String horaInicio;
    private String horaFim;

    public EventoResponse(Evento evento) {
        this.idEvento = evento.getIdEvento();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.local = evento.getLocal();
        this.linkReuniao = evento.getLinkReuniao();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        if (evento.getHoraInicio() != null) {
            this.horaInicio = evento.getHoraInicio().format(timeFormatter);
        }
        if (evento.getHoraFim() != null) {
            this.horaFim = evento.getHoraFim().format(timeFormatter);
        }

        if (evento.getAdvogado() != null) {
            this.nomeAdvogado = evento.getAdvogado().getNome();
        }
        if (evento.getCliente() != null) {
            this.nomeCliente = evento.getCliente().getNome();
        }
        if (evento.getCategoria() != null) {
            this.nomeCategoria = evento.getCategoria().getNome();
        }
        if (evento.getProcesso() != null) {
            this.numeroProcesso = evento.getProcesso().getNumeroProcesso();
        }
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLinkReuniao() {
        return linkReuniao;
    }

    public void setLinkReuniao(String linkReuniao) {
        this.linkReuniao = linkReuniao;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nomeAdvogado) {
        this.nomeAdvogado = nomeAdvogado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
}
