package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Lead;

public class LeadResponse {
    private Long idLead;
    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private String mensagem;

    public LeadResponse(Lead lead) {
        this.idLead = lead.getIdLead();
        this.nome = lead.getNome();
        this.telefone = lead.getTelefone();
        this.email = lead.getEmail();
        this.assunto = lead.getAssunto();
        this.mensagem = lead.getMensagem();
    }

    public Long getIdLead() {
        return idLead;
    }

    public void setIdLead(Long idLead) {
        this.idLead = idLead;
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
}
