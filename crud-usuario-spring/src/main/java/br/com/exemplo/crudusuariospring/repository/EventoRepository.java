package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Evento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCliente_IdCliente(Integer idCliente);
    List<Evento> findByAdvogadoIdAdvogado(Integer idAdvogado);
    List<Evento> findByAdvogadoIdAdvogadoAndDataReuniaoBetween(Integer idAdvogado, Date startDate, Date endDate);
    List<Evento> findByAdvogadoIdAdvogadoAndDataReuniaoAfterOrDataReuniaoEquals(
            Integer idAdvogado, Date afterDate, Date sameDate);


    @Modifying
    @Transactional
    @Query("UPDATE Evento e SET e.categoria = NULL WHERE e.categoria.idCategoria = :categoriaId")
    void desvincularCategoriaDosEventos(@Param("categoriaId") Long categoriaId);
}
