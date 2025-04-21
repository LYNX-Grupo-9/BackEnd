package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Processo;

public class ProcessoResponse {
    
    private Long idProcesso;
    private String numeroProcesso;
    private String descricao;
    private String status;
    private String nomeAdvogado;
    private String nomeCliente;

    public ProcessoResponse(Processo processo) {
        this.idProcesso = processo.getIdProcesso();
        this.numeroProcesso = processo.getNumeroProcesso();
        this.descricao = processo.getDescricao();
        this.status = processo.getStatus();

        if (processo.getAdvogado() != null) {
            this.nomeAdvogado = processo.getAdvogado().getNome();
        }

        if (processo.getCliente() != null) {
            this.nomeCliente = processo.getCliente().getNome();
        }
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nomeAdvogado) {
        this.nomeAdvogado = nomeAdvogado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
