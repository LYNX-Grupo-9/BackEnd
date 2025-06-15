package br.com.exemplo.crudusuariospring.observer.event;

import br.com.exemplo.crudusuariospring.model.Cliente;
import org.springframework.context.ApplicationEvent;

public class ClienteAtualizadoEvent extends ApplicationEvent {
    private final Cliente cliente;

    public ClienteAtualizadoEvent(Object source, Cliente cliente) {
        super(source);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
