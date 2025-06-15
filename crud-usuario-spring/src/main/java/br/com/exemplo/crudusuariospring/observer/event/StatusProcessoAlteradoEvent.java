package br.com.exemplo.crudusuariospring.observer.event;

import org.springframework.context.ApplicationEvent;

public class StatusProcessoAlteradoEvent extends ApplicationEvent {

    private final Long idProcesso;
    private final String novoStatus;

    public StatusProcessoAlteradoEvent(Object source, Long idProcesso, String novoStatus) {
        super(source);
        this.idProcesso = idProcesso;
        this.novoStatus = novoStatus;
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public String getNovoStatus() {
        return novoStatus;
    }
}
