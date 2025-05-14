package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.config.GerenciadorTokenJwt;
import br.com.exemplo.crudusuariospring.dto.AdvogadoDetalhes;
import br.com.exemplo.crudusuariospring.dto.AdvogadoMapper;
import br.com.exemplo.crudusuariospring.dto.request.AdvogadoRequest;
import br.com.exemplo.crudusuariospring.dto.request.ClienteRequest;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoToken;
import br.com.exemplo.crudusuariospring.dto.response.ClienteResponse;
import br.com.exemplo.crudusuariospring.model.Advogado;
import br.com.exemplo.crudusuariospring.model.Cliente;
import br.com.exemplo.crudusuariospring.observer.CadastroAdvogadoSubject;
import br.com.exemplo.crudusuariospring.observer.EmailObserver;
import br.com.exemplo.crudusuariospring.observer.LogObserver;
import br.com.exemplo.crudusuariospring.repository.AdvogadoRepository;
import br.com.exemplo.crudusuariospring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.*;
import br.com.exemplo.crudusuariospring.dto.response.AdvogadoResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdvogadoService {

    @Autowired
    private AdvogadoRepository advogadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClienteRepository clienteRepository;

    private CadastroAdvogadoSubject subject;

    public AdvogadoService() {
        subject = new CadastroAdvogadoSubject();
        subject.adicionarObserver(new EmailObserver());
        subject.adicionarObserver(new LogObserver());
    }

    private Advogado toEntity(AdvogadoRequest dto) {
        Advogado advogado = new Advogado();
        advogado.setNome(dto.getNome());
        advogado.setRegistroOab(dto.getRegistroOab());
        advogado.setCpf(dto.getCpf());
        advogado.setEmail(dto.getEmail());
        advogado.setSenha(dto.getSenha());
        return advogado;
    }

    private AdvogadoResponse toResponse(Advogado advogado) {
        AdvogadoResponse response = new AdvogadoResponse();
        response.setIdAdvogado(advogado.getIdAdvogado());
        response.setNome(advogado.getNome());
        response.setEmail(advogado.getEmail());
        response.setRegistroOab(advogado.getRegistroOab());
        return response;
    }

    public AdvogadoResponse salvar(AdvogadoRequest request) {
        Advogado advogado = toEntity(request);
        Advogado salvo = advogadoRepository.save(advogado);

        String nomeAdvogado = salvo.getNome();
        subject.notificarTodos("Novo advogado cadastrado: " + nomeAdvogado);

        return toResponse(salvo);
    }

    public void criarAdvogado(Advogado novoAdvogado) {
        String senhaCriptografada = passwordEncoder.encode(novoAdvogado.getSenha());
        novoAdvogado.setSenha(senhaCriptografada);

        this.advogadoRepository.save(novoAdvogado);
    }

    public AdvogadoToken autenticar(Advogado advogado) {
        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(advogado.getEmail(), advogado.getSenha());

        final Authentication authentication = authenticationManager.authenticate(credentials);

        Advogado advogadoAutenticado = advogadoRepository
                .findByEmail(advogado.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Advogado não encontrado"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.gerarToken(authentication);

        return AdvogadoMapper.of(advogadoAutenticado, token);
    }

    public List<AdvogadoResponse> listarTodosAdvogados(){
        List<Advogado> advogadosEncontrados = advogadoRepository.findAll();
        return advogadosEncontrados.stream()
                .map(AdvogadoMapper::of)
                .toList();
    }

    public AdvogadoResponse buscarPorEmail(String email) {
        Advogado advogado = advogadoRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Advogado não encontrado"));
        return AdvogadoMapper.of(advogado);
    }

    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String emailAdvogado = userDetails.getUsername();

            Advogado advogado = advogadoRepository.findByEmail(emailAdvogado)
                    .orElseThrow(() -> new RuntimeException("Advogado não encontrado"));

            // Verificando duplicidade de email e documento
            if (clienteRepository.existsByEmail(clienteRequest.getEmail())) {
                throw new RuntimeException("Cliente com email já cadastrado");
            }

            if (clienteRepository.existsByDocumento(clienteRequest.getDocumento())) {
                throw new RuntimeException("Cliente com documento já cadastrado");
            }

            Cliente cliente = new Cliente();
            cliente.setNome(clienteRequest.getNome());
            cliente.setDocumento(clienteRequest.getDocumento());
            cliente.setTipoDocumento(clienteRequest.getTipoDocumento());
            cliente.setEmail(clienteRequest.getEmail());
            cliente.setTelefone(clienteRequest.getTelefone());
            cliente.setEndereco(clienteRequest.getEndereco());
            cliente.setGenero(clienteRequest.getGenero());
            cliente.setDataNascimento(clienteRequest.getDataNascimento());
            cliente.setEstadoCivil(clienteRequest.getEstadoCivil());
            cliente.setProfissao(clienteRequest.getProfissao());
            cliente.setPassaporte(clienteRequest.getPassaporte());
            cliente.setCnh(clienteRequest.getCnh());
            cliente.setNaturalidade(clienteRequest.getNaturalidade());
            cliente.setAdvogado(advogado);

            Cliente clienteSalvo = clienteRepository.save(cliente);

            // Mapear os dados para ClienteResponse
            ClienteResponse clienteResponse = new ClienteResponse();
            clienteResponse.setIdCliente(clienteSalvo.getIdCliente());
            clienteResponse.setNome(clienteSalvo.getNome());
            clienteResponse.setEmail(clienteSalvo.getEmail());
            clienteResponse.setTelefone(clienteSalvo.getTelefone());
            clienteResponse.setEndereco(clienteSalvo.getEndereco());
            clienteResponse.setGenero(clienteSalvo.getGenero());
            clienteResponse.setDataNascimento(clienteSalvo.getDataNascimento());
            clienteResponse.setEstadoCivil(clienteSalvo.getEstadoCivil());
            clienteResponse.setProfissao(clienteSalvo.getProfissao());
            clienteResponse.setPassaporte(clienteSalvo.getPassaporte());
            clienteResponse.setCnh(clienteSalvo.getCnh());
            clienteResponse.setNaturalidade(clienteSalvo.getNaturalidade());
            clienteResponse.setAdvogadoResponsavel(advogado.getNome());

            return clienteResponse;
        }

        throw new RuntimeException("Não foi possível obter os dados do advogado autenticado");
    }
}
