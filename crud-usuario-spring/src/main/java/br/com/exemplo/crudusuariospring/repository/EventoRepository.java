package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCliente_IdCliente(Integer idCliente);
    List<Evento> findByAdvogadoIdAdvogado(Integer idAdvogado);
    List<Evento> findByAdvogadoIdAdvogadoAndDataReuniaoBetween(Integer idAdvogado, LocalTime startDate, LocalTime endDate);
}
