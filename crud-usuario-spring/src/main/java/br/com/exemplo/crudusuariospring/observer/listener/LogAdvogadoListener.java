package br.com.exemplo.crudusuariospring.observer.listener;

import br.com.exemplo.crudusuariospring.observer.event.CadastroAdvogadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogAdvogadoListener {

    @EventListener
    public void logCriacaoAdvogado(CadastroAdvogadoEvent event) {
        System.out.println("[LOG] Advogado criado: " + event.getNomeAdvogado());
    }
}
