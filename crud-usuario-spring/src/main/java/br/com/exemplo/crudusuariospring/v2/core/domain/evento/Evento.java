package br.com.exemplo.crudusuariospring.v2.core.domain.evento;

import br.com.exemplo.crudusuariospring.model.Processo;

import java.sql.Date;
import java.time.LocalTime;

public class Evento {

    private String nome;
    private String descricao;
    private String local;
    private String linkReuniao;
    private Date dataReuniao;
    private LocalTime horaInicio;
    private LocalTime horaFim;

//    private Advogado advogado;
//    private Cliente cliente;
//    private CategoriaEvento categoria;
//    private Processo processo;


    public Evento(String nome, String descricao, String local, String linkReuniao, Date dataReuniao, LocalTime horaInicio, LocalTime horaFim) {
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.linkReuniao = linkReuniao;
        this.dataReuniao = dataReuniao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
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

    public Date getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(Date dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}
