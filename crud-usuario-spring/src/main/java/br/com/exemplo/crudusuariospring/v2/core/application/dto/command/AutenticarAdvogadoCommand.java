package br.com.exemplo.crudusuariospring.v2.core.application.dto.command;

public record AutenticarAdvogadoCommand(
        String email,
        String senha
) {
}
