package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

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

    // RELACIONAMENTOS
    @OneToMany(mappedBy = "advogado")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "advogado")
    private List<Processo> processos;

    @OneToMany(mappedBy = "advogado")
    private List<Evento> eventos;

    @OneToMany(mappedBy = "advogado")
    private List<Lead> leads;

    @OneToMany(mappedBy = "advogado")
    private List<SolicitacaoAgendamento> solicitacoesAgendamento;

    @OneToMany(mappedBy = "advogado")
    private List<CategoriaEvento> categoriasEvento;

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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }

    public List<SolicitacaoAgendamento> getSolicitacoesAgendamento() {
        return solicitacoesAgendamento;
    }

    public void setSolicitacoesAgendamento(List<SolicitacaoAgendamento> solicitacoesAgendamento) {
        this.solicitacoesAgendamento = solicitacoesAgendamento;
    }

    public List<CategoriaEvento> getCategoriasEvento() {
        return categoriasEvento;
    }

    public void setCategoriasEvento(List<CategoriaEvento> categoriasEvento) {
        this.categoriasEvento = categoriasEvento;
    }
}
