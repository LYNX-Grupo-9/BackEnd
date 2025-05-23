package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Processo;

public class ProcessoResponse {
    
    private Long idProcesso;
    private String numeroProcesso;
    private String descricao;
    private String status;
    private Integer idAdvogado;
    private Integer idCliente;

    public ProcessoResponse(Processo processo) {
        this.idProcesso = processo.getIdProcesso();
        this.numeroProcesso = processo.getNumeroProcesso();
        this.descricao = processo.getDescricao();
        this.status = processo.getStatus();

        if (processo.getAdvogado() != null) {
            this.idAdvogado = processo.getAdvogado().getIdAdvogado();
        }

        if (processo.getCliente() != null) {
            this.idCliente = processo.getCliente().getIdCliente();
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

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
