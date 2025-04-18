package br.com.exemplo.crudusuariospring.dto;

import br.com.exemplo.crudusuariospring.model.Advogado;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AdvogadoDetalhes implements UserDetails {

    private final String nome;
    private final String email;
    private final String senha;

    public AdvogadoDetalhes(Advogado advogado) {
        this.nome = advogado.getNome();
        this.email = advogado.getEmail();
        this.senha = advogado.getSenha();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public Collection<?extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
