package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CategoriaEventoRequest {

    @NotBlank(message = "O nome do Evento é obrigatório")
    private String nomeEvento;
    @NotBlank(message = "A cor é obrigatória")
    private String cor;

    public CategoriaEventoRequest(String nomeEvento, String cor) {
        this.nomeEvento = nomeEvento;
        this.cor = cor;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
