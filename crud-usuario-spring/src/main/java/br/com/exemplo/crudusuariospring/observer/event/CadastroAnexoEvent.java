package br.com.exemplo.crudusuariospring.observer.event;

import br.com.exemplo.crudusuariospring.model.Anexo;
import br.com.exemplo.crudusuariospring.model.Cliente;
import org.springframework.context.ApplicationEvent;

public class CadastroAnexoEvent extends ApplicationEvent {
    private final Anexo anexo;

    public CadastroAnexoEvent(Object source, Anexo anexo) {
        super(source);
        this.anexo = anexo;
    }

    public Anexo getAnexo() {
        return anexo;
    }
}
