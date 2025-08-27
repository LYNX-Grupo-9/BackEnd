package br.com.exemplo.crudusuariospring.v2.infraestructure.di;

import br.com.exemplo.crudusuariospring.v2.core.application.usecase.CriarAdvogadoUseCase;
import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.adapter.AdvogadoJpaAdapter;
import br.com.exemplo.crudusuariospring.v2.infraestructure.persistence.jpa.repository.AdvogadoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvogadoBeanConfig {

    @Bean
    public CriarAdvogadoUseCase criarAdvogadoUseCase(AdvogadoJpaAdapter adapter) {
        return new CriarAdvogadoUseCase(adapter);
    }

}
