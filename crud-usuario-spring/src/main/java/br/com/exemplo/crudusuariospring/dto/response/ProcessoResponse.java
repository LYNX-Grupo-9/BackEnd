package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Processo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessoResponse {

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
    private Long idAdvogado;
    private String nomeAdvogado;
    private Long idCliente;
    private String nomeCliente;
    private List<Long> eventos;

    public ProcessoResponse(Processo processo) {
        this.idProcesso = processo.getIdProcesso();
        this.titulo = processo.getTitulo();
        this.numeroProcesso = processo.getNumeroProcesso();
        this.descricao = processo.getDescricao();
        this.status = processo.getStatus();
        this.classeProcessual = processo.getClasseProcessual();
        this.assunto = processo.getAssunto();
        this.tribunal = processo.getTribunal();
        this.valor = processo.getValor();
        this.autor = processo.getAutor();
        this.advRequerente = processo.getAdvRequerente();
        this.reu = processo.getReu();
        this.advReu = processo.getAdvReu();

        this.eventos = processo.getEventos() != null ?
                processo.getEventos().stream()
                        .map(evento -> evento.getIdEvento())
                        .collect(Collectors.toList()) :
                null;
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClasseProcessual() {
        return classeProcessual;
    }

    public void setClasseProcessual(String classeProcessual) {
        this.classeProcessual = classeProcessual;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTribunal() {
        return tribunal;
    }

    public void setTribunal(String tribunal) {
        this.tribunal = tribunal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAdvRequerente() {
        return advRequerente;
    }

    public void setAdvRequerente(String advRequerente) {
        this.advRequerente = advRequerente;
    }

    public String getReu() {
        return reu;
    }

    public void setReu(String reu) {
        this.reu = reu;
    }

    public String getAdvReu() {
        return advReu;
    }

    public void setAdvReu(String advReu) {
        this.advReu = advReu;
    }

    public Long getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Long idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nomeAdvogado) {
        this.nomeAdvogado = nomeAdvogado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Long> getEventos() {
        return eventos;
    }

    public void setEventos(List<Long> eventos) {
        this.eventos = eventos;
    }
}