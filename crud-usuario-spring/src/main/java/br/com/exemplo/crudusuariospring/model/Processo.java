package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idProcesso;
    private String titulo;
    private String numeroProcesso;
    private String descricao;
    private String status;
    private String classeProcessual;
    private String assunto;
    private String tribunal;

    private BigDecimal valor;
    private String autor;
    private String advRequerente;
    private String reu;
    private String advReu;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "id_advogado")
    private Advogado advogado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "processo")
    private List<Evento> eventos;

    @ManyToOne
    @JoinColumn(name = "id_anexo")
    private Anexo anexo;

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public String getAdvReu() {
        return advReu;
    }

    public void setAdvReu(String advReu) {
        this.advReu = advReu;
    }

    public String getReu() {
        return reu;
    }

    public void setReu(String reu) {
        this.reu = reu;
    }

    public String getAdvRequerente() {
        return advRequerente;
    }

    public void setAdvRequerente(String advRequerente) {
        this.advRequerente = advRequerente;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTribunal() {
        return tribunal;
    }

    public void setTribunal(String tribunal) {
        this.tribunal = tribunal;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getClasseProcessual() {
        return classeProcessual;
    }

    public void setClasseProcessual(String classeProcessual) {
        this.classeProcessual = classeProcessual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
}
