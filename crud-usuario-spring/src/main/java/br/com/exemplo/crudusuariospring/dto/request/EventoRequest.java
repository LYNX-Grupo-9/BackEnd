package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


import java.time.LocalDateTime;

public class EventoRequest {

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
    @NotBlank(message = "A data e hora são obrigatórias.")
    private LocalDateTime dataHora;
    @NotBlank (message = "O local é obrigatório")
    private String local;

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
}
