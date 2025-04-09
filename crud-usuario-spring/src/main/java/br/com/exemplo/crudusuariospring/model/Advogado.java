package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Advogado {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer idAdvogado;

    private String nome;
    private String registroOab;
    private String cpf;
    private String email;
    private String senha;

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public String getNome() {
        return nome;
    }

    public String getRegistroOab() {
        return registroOab;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegistroOab(String registroOab) {
        this.registroOab = registroOab;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
