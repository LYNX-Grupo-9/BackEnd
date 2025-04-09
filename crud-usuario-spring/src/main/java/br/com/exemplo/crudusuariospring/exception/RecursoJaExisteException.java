package br.com.exemplo.crudusuariospring.exception;

public class RecursoJaExisteException extends RuntimeException {
    public RecursoJaExisteException(String mensagem) {
        super(mensagem);
    }
}