package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class SolicitacaoAgendamento {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idSolicitacaoAgendamento;

    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private LocalDate dataSolicitacao;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "idAdvogado")
    private Advogado advogado;


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

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }
}
