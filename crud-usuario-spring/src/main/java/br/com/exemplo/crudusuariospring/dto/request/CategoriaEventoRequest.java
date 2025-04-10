package br.com.exemplo.crudusuariospring.dto.request;

public class CategoriaEventoRequest {

    private String nomeEvento;
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
