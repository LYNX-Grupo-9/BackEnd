package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCliente_IdCliente(Integer idCliente);
}
