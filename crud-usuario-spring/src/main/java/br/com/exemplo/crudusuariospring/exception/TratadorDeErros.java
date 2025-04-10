package br.com.exemplo.crudusuariospring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RecursoJaExisteException.class)
    public ResponseEntity<String> tratarRecursoJaExiste(RecursoJaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErroGeral(Exception ex) {
        String nomeDaClasse = ex.getClass().getName();

        if (nomeDaClasse.startsWith("org.springdoc") || nomeDaClasse.startsWith("org.springframework")) {
            throw new RuntimeException(ex);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado: " + ex.getMessage());
    }
}