package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.model.Usuario;
import br.com.exemplo.crudusuariospring.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuariosPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "Usuário deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
        return usuarioRepository.findById(id)
                .map(usuario->{
                    usuario.setNome(novoUsuario.getNome());
                    usuario.setCpf(novoUsuario.getCpf());
                    usuario.setEmail(novoUsuario.getEmail());
                    usuario.setSenha(novoUsuario.getSenha());
                    usuario.setTelefone(novoUsuario.getTelefone());
                    usuario.setOab(novoUsuario.getOab());
                    usuario.setAreaAtuacao(novoUsuario.getAreaAtuacao());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}
