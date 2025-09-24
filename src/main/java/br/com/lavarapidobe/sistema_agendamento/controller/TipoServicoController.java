package br.com.lavarapidobe.sistema_agendamento.controller;

import br.com.lavarapidobe.sistema_agendamento.model.TipoServico;
import br.com.lavarapidobe.sistema_agendamento.service.TipoServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-servicos")
public class TipoServicoController {

    public final TipoServicoService service;

    public TipoServicoController(TipoServicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoServico> criar(@RequestBody TipoServico tipoServico) {
        return ResponseEntity.status(201).body(service.salvar(tipoServico));
    }

    @GetMapping
    public ResponseEntity<List<TipoServico>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoServico> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
