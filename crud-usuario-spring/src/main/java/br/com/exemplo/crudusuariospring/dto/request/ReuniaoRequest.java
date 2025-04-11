package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class ReuniaoRequest {

    @NotBlank(message = "A descrição da reunião é obrigatória")
    private String descricaoReuniao;
    @NotBlank(message = "O local é obrigatório")
    private String local;
    @NotBlank(message = "Não pode estar em branco a Data e hora de início")
    private LocalDateTime dataHoraInicio;
    @NotBlank(message = "Não pode estar em branco a Data e hora de término")
    private LocalDateTime dataHoraFim;

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
