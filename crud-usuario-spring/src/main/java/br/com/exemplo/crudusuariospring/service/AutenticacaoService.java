package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.AdvogadoDetalhes;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Advogado> advogadoOpt = advogadoRepository.findByEmail(username);

        if (advogadoOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
        }

        return new AdvogadoDetalhes(advogadoOpt.get());
    }

}
