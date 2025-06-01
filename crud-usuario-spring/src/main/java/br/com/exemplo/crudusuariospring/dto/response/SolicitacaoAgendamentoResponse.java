package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class SolicitacaoAgendamentoResponse {

    private Long idSolicitacaoAgendamento;
    private String nome;
    private String email;
    private String assunto;
    private String telefone;
    private Date dataSolicitacao;
    private LocalTime horaSolicitacao;
    private Boolean visualizado;
    private Integer idAvogado;

    public SolicitacaoAgendamentoResponse(SolicitacaoAgendamento solicitacaoAgendamento) {
        this.idSolicitacaoAgendamento = solicitacaoAgendamento.getIdSolicitacaoAgendamento();
        this.nome = solicitacaoAgendamento.getNome();
        this.telefone = solicitacaoAgendamento.getTelefone();
        this.email = solicitacaoAgendamento.getEmail();
        this.assunto = solicitacaoAgendamento.getAssunto();
        this.dataSolicitacao = solicitacaoAgendamento.getDataSolicitacao();
        this.visualizado = solicitacaoAgendamento.getVisualizado();
        this.horaSolicitacao = solicitacaoAgendamento.getHoraSolicitacao();
        this.idAvogado = solicitacaoAgendamento.getAdvogado().getIdAdvogado();
    }

    public Long getIdSolicitacaoAgendamento() {
        return idSolicitacaoAgendamento;
    }

    public void setIdSolicitacaoAgendamento(Long idSolicitacaoAgendamento) {
        this.idSolicitacaoAgendamento = idSolicitacaoAgendamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalTime getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(LocalTime horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public Boolean getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(Boolean visualizado) {
        this.visualizado = visualizado;
    }

    public Integer getIdAvogado() {
        return idAvogado;
    }

    public void setIdAvogado(Integer idAvogado) {
        this.idAvogado = idAvogado;
    }
}
