package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Evento;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EventoResponse {

    private Long idEvento;
    private String nome;
    private String descricao;
    private String local;
    private String linkReuniao;
    private Integer idAdvogado;
    private Integer idCliente;
    private String cor;
    private Integer idCategoria;
    private Long idProcesso;
    private String dataReuniao;
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
            this.idAdvogado = evento.getAdvogado().getIdAdvogado();
        }

        if (evento.getCliente() != null) {
            this.idCliente = evento.getCliente().getIdCliente();
        }

        if (evento.getCategoria() != null) {
            this.cor = evento.getCategoria().getCor();
        }

        if (evento.getProcesso() != null) {
            this.idProcesso = evento.getProcesso().getIdProcesso();
        }

        if (evento.getDataReuniao() != null) {
            LocalDate dataLocal = evento.getDataReuniao().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.dataReuniao = dataLocal.format(dateFormatter);
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

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
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

    public String getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(String dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}