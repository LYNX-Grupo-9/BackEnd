package br.com.exemplo.crudusuariospring.dto.response;

public class CategoriaEventoResponse {

    private Long idCategoriaEvento;
    private String nomeEvento;
    private String cor;

    public CategoriaEventoResponse(Long idCategoriaEvento, String nomeEvento, String cor) {
        this.idCategoriaEvento = idCategoriaEvento;
        this.nomeEvento = nomeEvento;
        this.cor = cor;
    }

    public Long getIdCategoriaEvento() {
        return idCategoriaEvento;
    }

    public void setIdCategoriaEvento(Long idCategoriaEvento) {
        this.idCategoriaEvento = idCategoriaEvento;
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
