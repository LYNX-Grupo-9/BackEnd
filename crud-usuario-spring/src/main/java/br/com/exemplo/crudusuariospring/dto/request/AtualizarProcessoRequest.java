package br.com.exemplo.crudusuariospring.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtualizarProcessoRequest {

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
    private Integer idAdvogado;
    private Integer idCliente;

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

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
