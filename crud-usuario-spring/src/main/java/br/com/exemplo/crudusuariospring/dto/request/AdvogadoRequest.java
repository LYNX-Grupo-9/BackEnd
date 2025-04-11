package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AdvogadoRequest {

    @NotBlank (message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O registro da OAB é obrigatório.")
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "O registro da OAB deve estar no formato UF + 6 dígitos. Ex: SP123456.")
    private String registroOab;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$", message = "CPF deve estar no formato xxx.xxx.xxx-xx ou conter apenas 11 dígitos.")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroOab() {
        return registroOab;
    }

    public void setRegistroOab(String registroOab) {
        this.registroOab = registroOab;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
