package br.com.exemplo.crudusuariospring.dto.response;

import br.com.exemplo.crudusuariospring.model.Anexo;

public class AnexoResponse {

    private Long idAnexo;
    private String nomeAnexo;
    private String idItem;

    public AnexoResponse(Anexo anexo) {
        this.idAnexo = anexo.getIdAnexo();
        this.nomeAnexo = anexo.getNomeAnexo();
        this.idItem = anexo.getIdItem();
    }

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
}

