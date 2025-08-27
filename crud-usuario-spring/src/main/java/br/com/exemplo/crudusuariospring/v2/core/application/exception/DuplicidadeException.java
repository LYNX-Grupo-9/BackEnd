package br.com.exemplo.crudusuariospring.v2.core.application.exception;

public class DuplicidadeException extends RuntimeException {
    public DuplicidadeException(String message) {
        super(message);
    }
}
