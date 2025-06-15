package br.com.exemplo.crudusuariospring.observer.listener;

import br.com.exemplo.crudusuariospring.observer.event.ClienteCadastradoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogClienteListener {

    @EventListener
    public void logCadastroCliente(ClienteCadastradoEvent event) {
        System.out.printf("[LOG] Cliente cadastrado - " + event.getCliente().getNome());
    }

    @EventListener
    public void logAtualizacaoCliente(ClienteCadastradoEvent event) {
        System.out.printf("[LOG] Cliente atualizado - " + event.getCliente().getIdCliente());
    }
}
