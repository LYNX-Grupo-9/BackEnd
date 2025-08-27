package br.com.exemplo.crudusuariospring.v2.core.adapter.gateway;


import br.com.exemplo.crudusuariospring.v2.core.domain.advogado.Advogado;

public interface AdvogadoGateway {
    Advogado criar(Advogado domain);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    boolean existePorOab(String oab);
}
