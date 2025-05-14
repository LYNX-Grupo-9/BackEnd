package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import br.com.exemplo.crudusuariospring.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCliente_IdCliente(Integer idCliente);
    List<Evento> findByAdvogadoIdAdvogado(Integer idAdvogado);
    List<Evento> findByAdvogadoIdAdvogadoAndDataHoraBetween(Integer idAdvogado, LocalDateTime startDate, LocalDateTime endDate);
}
