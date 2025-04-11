package br.com.exemplo.crudusuariospring.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AnexoRequest {

    @NotBlank(message = "O nome do anexo é obrigatório.")
    private String nomeAnexo;
    @NotBlank(message = "O link do bucket é obrigatório.")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "O link do bucket deve ser uma URL válida."
    )
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
