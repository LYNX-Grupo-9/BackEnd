package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.CategoriaEvento;

public class  CategoriaEventoResponse {

    private Long idCategoriaEvento;
    private String nomeEvento;
    private String cor;

    public CategoriaEventoResponse(Long idCategoriaEvento, String nomeEvento, String cor) {
        this.idCategoriaEvento = idCategoriaEvento;
        this.nomeEvento = nomeEvento;
        this.cor = cor;
    }

    public CategoriaEventoResponse(CategoriaEvento categoria) {
        this.idCategoriaEvento = categoria.getIdCategoria();
        this.nomeEvento = categoria.getNome();
        this.cor = categoria.getCor();
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
