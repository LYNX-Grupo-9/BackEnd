package br.com.exemplo.crudusuariospring.dto.response;

public class AnexoResponse {

    private Long idAnexo;
    private String nomeAnexo;
    private String linkBucket;

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

    public String getLinkBucket() {
        return linkBucket;
    }

    public void setLinkBucket(String linkBucket) {
        this.linkBucket = linkBucket;
    }
}
