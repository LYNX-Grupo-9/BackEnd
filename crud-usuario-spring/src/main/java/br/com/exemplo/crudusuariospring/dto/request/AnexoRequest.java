package br.com.exemplo.crudusuariospring.dto.request;

public class AnexoRequest {

    private String nomeAnexo;
    private String linkBucket;

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
