package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ClienteRequest {

    @NotBlank (message = "O nome é obrigatório")
    private String nome;
    @NotBlank (message = "O CPF é obrigatório")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$", message = "CPF deve estar no formato xxx.xxx.xxx-xx ou conter apenas 11 dígitos.")
    private String cpf;
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    private String email;
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$", message = "O telefone deve estar no formato 11912345678.")
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
