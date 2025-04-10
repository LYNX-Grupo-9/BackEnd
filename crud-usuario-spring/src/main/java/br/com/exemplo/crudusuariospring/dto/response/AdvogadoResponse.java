package br.com.exemplo.crudusuariospring.dto.response;

public class AdvogadoResponse {

    private Integer idAdvogado;
    private String nome;
    private String email;
    private String registroOab;

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
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

    public String getRegistroOab() {
        return registroOab;
    }

    public void setRegistroOab(String registroOab) {
        this.registroOab = registroOab;
    }
}
