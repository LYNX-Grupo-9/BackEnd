package br.com.exemplo.crudusuariospring.v2.core.application.usecase;

import br.com.exemplo.crudusuariospring.v2.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudusuariospring.v2.core.application.dto.command.CriarAdvogadoCommand;
import br.com.exemplo.crudusuariospring.v2.core.application.dto.response.CriarAdvogadoResponse;
import br.com.exemplo.crudusuariospring.v2.core.application.exception.DuplicidadeException;
import br.com.exemplo.crudusuariospring.v2.core.domain.Advogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriarAdvogadoUseCase {

    private final AdvogadoGateway advogadoGateway;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CriarAdvogadoUseCase(AdvogadoGateway advogadoGateway) {
        this.passwordEncoder = passwordEncoder;
        this.advogadoGateway = advogadoGateway;
    }

    public CriarAdvogadoResponse excutar(CriarAdvogadoCommand command) {
        String senhaCriptografada = passwordEncoder.encode(command.senha());

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
                senhaCriptografada
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
