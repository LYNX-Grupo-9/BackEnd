package br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.adapter;

import br.com.exemplo.crudusuariospring.v2.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudusuariospring.v2.core.domain.advogado.Advogado;
import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.mapper.AdvogadoMapper;
import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.repository.AdvogadoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdvogadoJpaAdapter implements AdvogadoGateway {

    private final AdvogadoJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public AdvogadoJpaAdapter(AdvogadoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Advogado criar(Advogado domain) {
        AdvogadoEntity entityParaRegistro = AdvogadoMapper.toEntity(domain);
        entityManager.persist(entityParaRegistro); // salva com o ID do domínio
        return domain;
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public boolean existePorOab(String oab) {
        return repository.existsByRegistroOab(oab);
    }


}
