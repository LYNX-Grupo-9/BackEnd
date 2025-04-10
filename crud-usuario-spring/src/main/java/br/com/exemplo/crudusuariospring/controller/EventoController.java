package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.EventoRequest;
import br.com.exemplo.crudusuariospring.dto.response.EventoResponse;
import br.com.exemplo.crudusuariospring.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

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
}
