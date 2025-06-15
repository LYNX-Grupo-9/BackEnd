package br.com.exemplo.crudusuariospring.observer.event;

import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import org.springframework.context.ApplicationEvent;

public class DeleteCategoriaEvent extends ApplicationEvent {
    private final CategoriaEvento categoriaEvento;

    public DeleteCategoriaEvent(Object source, CategoriaEvento categoriaEvento) {
        super(source);
        this.categoriaEvento = categoriaEvento;
    }

    public CategoriaEvento getCategoriaEvento() {
        return categoriaEvento;
    }
}
