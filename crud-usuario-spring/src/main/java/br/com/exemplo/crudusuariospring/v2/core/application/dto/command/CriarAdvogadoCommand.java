package br.com.exemplo.crudusuariospring.v2.core.application.dto.command;

public record CriarAdvogadoCommand (
        String nome,
        String email,
        String cpf,
        String oab,
        String senha
){}