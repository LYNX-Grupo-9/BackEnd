package br.com.exemplo.crudusuariospring.dto.request;

public class AdvogadoRequest {

    private String nome;
    private String registroOab;
    private String cpf;
    private String email;
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
