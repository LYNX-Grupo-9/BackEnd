package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoriaEvento {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idCategoria;

    private String nomeEvento;
    private String cor;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "idAdvogado")
    private Advogado advogado;

    @OneToMany(mappedBy = "categoria")
    private List<Evento> eventos;


    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
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

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
