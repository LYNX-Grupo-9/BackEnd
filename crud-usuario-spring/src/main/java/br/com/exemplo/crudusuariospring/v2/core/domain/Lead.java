package br.com.exemplo.crudusuariospring.v2.core.domain;

import br.com.exemplo.crudusuariospring.v2.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudusuariospring.v2.core.domain.valueObjects.shared.Telefone;

public class Lead {
    private String nome;
    private Telefone telefone;
    private Email email;
    private String assunto;
    private String mensagem;

    // RELACIONAMENTOS
    private Advogado advogado;
}
