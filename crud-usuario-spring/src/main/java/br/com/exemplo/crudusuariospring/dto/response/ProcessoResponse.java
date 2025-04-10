package br.com.exemplo.crudusuariospring.dto.response;

public class ProcessoResponse {

    private Long idProcesso;
    private String numeroProcesso;
    private String descricao;
    private String status;

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long id) {
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
}
