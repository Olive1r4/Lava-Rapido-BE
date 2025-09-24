package br.com.lavarapidobe.sistema_agendamento.controller;

import br.com.lavarapidobe.sistema_agendamento.dto.UsuarioDTO;
import br.com.lavarapidobe.sistema_agendamento.mapper.AgendamentoMapper;
import br.com.lavarapidobe.sistema_agendamento.model.Usuario;
import br.com.lavarapidobe.sistema_agendamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final AgendamentoMapper mapper;

    public UsuarioController(UsuarioService service, AgendamentoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = service.salvar(usuario);
        UsuarioDTO responseDTO = mapper.toDTO(usuarioSalvo);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> usuariosDTO = service.listar().stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(mapper::toDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
