package br.com.exemplo.crudusuariospring.observer.listener;

import br.com.exemplo.crudusuariospring.observer.event.CadastroAdvogadoEvent;
import br.com.exemplo.crudusuariospring.observer.event.CadastroAnexoEvent;
import org.springframework.context.event.EventListener;

public class LogAnexoListener {

    @EventListener
    public void logCriacaoAnexo(CadastroAnexoEvent event) {
        System.out.println("[LOG] Anexo criado: " + event.getAnexo().getNomeAnexo());
    }
}
