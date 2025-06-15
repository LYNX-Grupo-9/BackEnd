package br.com.exemplo.crudusuariospring.observer.listener;

import br.com.exemplo.crudusuariospring.observer.event.CriacaoCategoriaEvent;
import org.springframework.context.event.EventListener;

public class LogCategoriaEventoListener {
    @EventListener
    public void logCriacaoCategoria(CriacaoCategoriaEvent event) {
        System.out.println("[LOG] Categoria criada: " + event.getCategoriaEvento().getNome());
    }

    @EventListener
    public void logDeleteCategoria(CriacaoCategoriaEvent event) {
        System.out.println("[LOG] Categoria excluída: " + event.getCategoriaEvento().getNome());
    }
}
