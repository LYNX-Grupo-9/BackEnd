package br.com.exemplo.crudusuariospring.v2.core.application.dto.response;

import java.util.UUID;

public record CriarAdvogadoResponse(
        UUID id,
        String nome,
        String email,
        String cpf,
        String oab
) {
}
