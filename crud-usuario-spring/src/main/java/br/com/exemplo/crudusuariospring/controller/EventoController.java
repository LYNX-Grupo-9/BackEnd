package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.EventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.model.Evento;
import br.com.exemplo.crudusuariospring.repository.EventoRepository;
import br.com.exemplo.crudusuariospring.service.EventoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping
    public ResponseEntity<EventoResponse> criarEvento(@RequestBody EventoRequest request) {
        EventoResponse eventoResponse = eventoService.criarEvento(request);
        return ResponseEntity.ok(eventoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarEventoPorId(@PathVariable Long id) {
        EventoResponse eventoResponse = eventoService.buscarPorId(id);
        return ResponseEntity.ok(eventoResponse);
    }

    @GetMapping("/cliente/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EventoResponse>> listarEventosPorCliente(@PathVariable Integer idCliente) {
        List<Evento> eventos = eventoRepository.findByCliente_IdCliente(idCliente);

        if(eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EventoResponse> resposta = eventos.stream()
                .map(EventoResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

}
