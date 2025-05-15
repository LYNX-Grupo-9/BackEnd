package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.response.CategoriaEventoResponse;
import br.com.exemplo.crudusuariospring.model.CategoriaEvento;
import br.com.exemplo.crudusuariospring.repository.CategoriaEventoRepository;
import br.com.exemplo.crudusuariospring.service.CategoriaEventoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaEventoController {

    @Autowired
    private CategoriaEventoService categoriaEventoService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CategoriaEventoResponse> criarCategoria(@RequestBody CategoriaEventoResponse categoriaEventoResponse) {
        CategoriaEventoResponse catergoriaCriada = categoriaEventoService.criarCategoria(categoriaEventoResponse);
        return ResponseEntity.ok(catergoriaCriada);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CategoriaEventoResponse>> buscarTodasCategorias() {
        List<CategoriaEventoResponse> categorias = categoriaEventoService.buscarTodasCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CategoriaEventoResponse>> buscarPorAdvogado(@PathVariable Integer idAdvogado) {
        List<CategoriaEventoResponse> categorias = categoriaEventoService.CategoriaPorAdvogado(idAdvogado);
        return ResponseEntity.ok(categorias);
    }
}
