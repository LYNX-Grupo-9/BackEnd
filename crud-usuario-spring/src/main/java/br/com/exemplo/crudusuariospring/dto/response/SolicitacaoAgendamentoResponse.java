package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.SolicitacaoAgendamento;

import java.time.LocalDateTime;

public class SolicitacaoAgendamentoResponse {

    private Long idSolicitacaoAgendamento;
    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private String mensagem;
    private LocalDateTime dataSolicitacao;

    public SolicitacaoAgendamentoResponse(SolicitacaoAgendamento solicitacao) {
        this.idSolicitacaoAgendamento = solicitacao.getIdSolicitacaoAgendamento();
        this.nome = solicitacao.getNome();
        this.telefone = solicitacao.getTelefone();
        this.email = solicitacao.getEmail();
        this.assunto = solicitacao.getAssunto();
        this.mensagem = solicitacao.getMensagem();
        this.dataSolicitacao = solicitacao.getDataSolicitacao();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
