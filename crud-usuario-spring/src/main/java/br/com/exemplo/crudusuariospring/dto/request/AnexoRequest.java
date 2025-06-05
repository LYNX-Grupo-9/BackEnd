package br.com.exemplo.crudusuariospring.dto.request;

import jakarta.validation.constraints.NotBlank;

public class AnexoRequest {

    private Long idAnexo;
    @NotBlank(message = "O nome do anexo não pode estar vazio")
    private String nomeAnexo;
    @NotBlank(message = "O id do item não pode estar vazio")
    private String idItem;

    private Integer idCliente;
    private Integer idProcesso;

    public Long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
    }

    public String getNomeAnexo() {
        return nomeAnexo;
    }

    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }
}

