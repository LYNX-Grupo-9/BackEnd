package br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.mapper;

import br.com.exemplo.crudusuariospring.v2.core.domain.advogado.Advogado;
import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.entity.AdvogadoEntity;

import java.util.Objects;

public class AdvogadoMapper {

    public static AdvogadoEntity toEntity(Advogado domain) {
        if(Objects.isNull(domain)) {
            return null;
        }

        var entity = new AdvogadoEntity();
        entity.setIdAdvogado(domain.getIdAdvogado());
        entity.setNome(domain.getNome());
        entity.setRegistroOab(domain.getOab().getNumeroOab());
        entity.setCpf(domain.getCpf().getNumeroCpf());
        entity.setEmail(domain.getEmail().getEnderacoEmail());
        entity.setSenha(domain.getSenha().getValor());

        return entity;
    }

    public static Advogado toDomain(AdvogadoEntity entity) {
        if(Objects.isNull(entity)) {
            return null;
        }

        var domain = Advogado.criarNovo(
                entity.getNome(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getRegistroOab(),
                entity.getSenha()
        );

        return domain;
    }

}
