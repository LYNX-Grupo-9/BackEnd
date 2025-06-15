package br.com.exemplo.crudusuariospring.observer.event;

import org.springframework.context.ApplicationEvent;

public class ProcessoCriadoEvent extends ApplicationEvent {

    private final String numeroProcesso;

    public ProcessoCriadoEvent(Object source, String numeroProcesso) {
        super(source);
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }
}
