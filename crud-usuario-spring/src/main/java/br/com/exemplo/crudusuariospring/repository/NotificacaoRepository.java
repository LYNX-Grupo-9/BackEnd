package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
