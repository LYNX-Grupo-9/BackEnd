package br.com.exemplo.crudusuariospring.dto.response;

public class ProcessoResponse {

    private Long idProcesso;
    private String numeroProcesso;
    private String descricao;
    private String status;
    private AdvogadoResponse advogado;
    private ClienteResponse cliente;

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

    public AdvogadoResponse getAdvogado() {
        return advogado;
    }

    public void setAdvogado(AdvogadoResponse advogado) {
        this.advogado = advogado;
    }

    public ClienteResponse getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponse cliente) {
        this.cliente = cliente;
    }
}
