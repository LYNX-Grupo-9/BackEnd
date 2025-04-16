package br.com.exemplo.crudusuariospring.observer;

public class EmailObserver implements Observer{
    @Override
    public void notificar (String mensagem) {
        System.out.println("📧 [Email] " + mensagem);
    }
}
