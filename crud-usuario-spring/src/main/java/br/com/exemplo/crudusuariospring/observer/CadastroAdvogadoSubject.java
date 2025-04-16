package br.com.exemplo.crudusuariospring.observer;

import java.util.ArrayList;
import java.util.List;

public class CadastroAdvogadoSubject {
    private List<Observer> observers = new ArrayList<>();

    public void adicionarObserver(Observer observer) {
        observers.add(observer);
    }

    public void notificarTodos(String mensagem){
        for  (Observer observer : observers) {
            observer.notificar(mensagem);
        }
    }
}
