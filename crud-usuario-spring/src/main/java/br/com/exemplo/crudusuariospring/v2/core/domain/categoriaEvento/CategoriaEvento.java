package br.com.exemplo.crudusuariospring.v2.core.domain.categoriaEvento;

import br.com.exemplo.crudusuariospring.model.Evento;
import br.com.exemplo.crudusuariospring.v2.core.domain.advogado.Advogado;

import java.util.List;

public class CategoriaEvento {

    private String nome;
    private String cor;

//    private Advogado advogado;
//    private List<Evento> eventos;

    public CategoriaEvento(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
