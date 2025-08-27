package br.com.exemplo.crudusuariospring.v2.core.application.usecase;

import br.com.exemplo.crudusuariospring.v2.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudusuariospring.v2.core.application.dto.command.CriarAdvogadoCommand;
import br.com.exemplo.crudusuariospring.v2.core.application.dto.response.CriarAdvogadoResponse;
import br.com.exemplo.crudusuariospring.v2.core.application.exception.DuplicidadeException;
import br.com.exemplo.crudusuariospring.v2.core.domain.advogado.Advogado;

public class CriarAdvogadoUseCase {

    private final AdvogadoGateway advogadoGateway;

    public CriarAdvogadoUseCase(AdvogadoGateway advogadoGateway) {
        this.advogadoGateway = advogadoGateway;
    }

    public CriarAdvogadoResponse excutar(CriarAdvogadoCommand command) {

        if(advogadoGateway.existePorEmail(command.email())) {
            throw new DuplicidadeException("Email já cadastrado");
        }
        if(advogadoGateway.existePorCpf(command.cpf())) {
            throw new DuplicidadeException("CPF já cadastrado");
        }
        if(advogadoGateway.existePorOab(command.oab())) {
            throw new DuplicidadeException("OAB já cadastrado");
        }

        Advogado advogadoParaRegistrar = Advogado.criarNovo(
                command.nome(),
                command.oab(),
                command.cpf(),
                command.email(),
                command.senha()
        );

        Advogado advogadoCriado = advogadoGateway.criar(advogadoParaRegistrar);

        return new CriarAdvogadoResponse(
                advogadoCriado.getIdAdvogado(),
                advogadoCriado.getNome(),
                advogadoCriado.getEmail().getEnderacoEmail(),
                advogadoCriado.getCpf().getNumeroCpf(),
                advogadoCriado.getOab().getNumeroOab()
        );
    }
}
