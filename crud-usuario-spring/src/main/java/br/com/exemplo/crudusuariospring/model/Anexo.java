package br.com.exemplo.crudusuariospring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Anexo {

    @Id
    private Long idAnexo;
    private String nomeAnexo;
    private String linkBucket;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
