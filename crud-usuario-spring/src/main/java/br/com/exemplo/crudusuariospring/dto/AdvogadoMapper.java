package br.com.exemplo.crudusuariospring.dto;

import br.com.exemplo.crudusuariospring.dto.request.AdvogadoLogin;
import br.com.exemplo.crudusuariospring.dto.request.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoToken;
import br.com.exemplo.crudusuariospring.model.Advogado;

public class AdvogadoMapper {

    public static Advogado of (AdvogadoRequest advogadoRequest) {

        Advogado advogado = new Advogado();

        advogado.setNome(advogadoRequest.getNome());
        advogado.setEmail(advogadoRequest.getEmail());
        advogado.setSenha(advogadoRequest.getSenha());
        advogado.setCpf(advogadoRequest.getCpf());
        advogado.setRegistroOab(advogadoRequest.getRegistroOab());

        return advogado;
    }

    public static Advogado of (AdvogadoLogin advogadoLogin) {
        Advogado advogado = new Advogado();

        advogado.setEmail(advogadoLogin.getEmail());
        advogado.setSenha(advogadoLogin.getSenha());

        return advogado;
    }

    public static AdvogadoToken of (Advogado advogado, String token){
        AdvogadoToken advogadoToken = new AdvogadoToken();

        advogadoToken.setIdAdvogado(advogado.getIdAdvogado());
        advogadoToken.setNome(advogado.getNome());
        advogadoToken.setEmail(advogado.getEmail());
        advogadoToken.setToken(token);

        return advogadoToken;
    }

    public static AdvogadoResponse of (Advogado advogado) {
        AdvogadoResponse advogadoResponse = new AdvogadoResponse();

        advogadoResponse.setIdAdvogado(advogado.getIdAdvogado());
        advogadoResponse.setNome(advogado.getNome());
        advogadoResponse.setEmail(advogado.getEmail());
        advogadoResponse.setRegistroOab(advogado.getRegistroOab());

        return advogadoResponse;
    }

}
