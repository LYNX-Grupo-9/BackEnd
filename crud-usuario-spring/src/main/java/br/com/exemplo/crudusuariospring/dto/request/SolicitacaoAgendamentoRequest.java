package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class SolicitacaoAgendamentoRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$", message = "O telefone deve estar no formato 11912345678.")
    private String telefone;
    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Assunto é obrigatório")
    private String assunto;
    @NotBlank(message = "Mensagem é obrigatório")
    private String mensagem;
    @NotBlank(message = "Data de solicitação é obrigatório")
    private LocalDateTime dataSolicitacao;

    @NotNull(message = "ID do advogado é obrigatório")
    private Integer idAdvogado;

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

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }
}
