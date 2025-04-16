package br.com.exemplo.crudusuariospring.observer;

public class LogObserver implements Observer {
    @Override
    public void notificar (String mensagem) {
        System.out.println("[Log] " + mensagem);
    }
}
