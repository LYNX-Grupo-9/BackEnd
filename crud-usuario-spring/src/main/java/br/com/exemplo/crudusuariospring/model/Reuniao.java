package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reuniao {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idReuniao;

    private String descricaoReuniao;
    private String local;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public Reuniao() {}

    public Reuniao(String descricaoReuniao, String local, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.descricaoReuniao = descricaoReuniao;
        this.local = local;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }

    public Long getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(Long idReuniao) {
        this.idReuniao = idReuniao;
    }

    public String getDescricaoReuniao() {
        return descricaoReuniao;
    }

    public void setDescricaoReuniao(String descricaoReuniao) {
        this.descricaoReuniao = descricaoReuniao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
