package br.com.exemplo.crudusuariospring.observer.event;

import org.springframework.context.ApplicationEvent;

public class CadastroAdvogadoEvent extends ApplicationEvent {
    private final String nomeAdvogado;

    public CadastroAdvogadoEvent(Object source, String nomeAdvogado) {
        super(source);
        this.nomeAdvogado = nomeAdvogado;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }
}
