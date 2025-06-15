package br.com.exemplo.crudusuariospring.observer.listener;

import br.com.exemplo.crudusuariospring.observer.event.ProcessoCriadoEvent;
import br.com.exemplo.crudusuariospring.observer.event.StatusProcessoAlteradoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogProcessoListener {

    @EventListener
    public void logCriacaoProcesso(ProcessoCriadoEvent event) {
        System.out.println("[LOG] Processo criado número do processo: " + event.getNumeroProcesso());
    }

    @EventListener
    public void logAlteracaoStatus(StatusProcessoAlteradoEvent event) {
        System.out.println("[LOG] Status do processo ID "
                + event.getIdProcesso() + " alterado para: " + event.getNovoStatus());
    }
}