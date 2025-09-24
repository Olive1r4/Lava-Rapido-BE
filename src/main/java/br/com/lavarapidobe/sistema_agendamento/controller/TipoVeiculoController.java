package br.com.lavarapidobe.sistema_agendamento.controller;

import br.com.lavarapidobe.sistema_agendamento.model.TipoVeiculo;
import br.com.lavarapidobe.sistema_agendamento.service.TipoVeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-veiculos")
public class TipoVeiculoController {

    private final TipoVeiculoService service;

    public TipoVeiculoController(TipoVeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoVeiculo> criar(@RequestBody TipoVeiculo tipoVeiculo) {
        return ResponseEntity.status(201).body(service.salvar(tipoVeiculo));
    }

    @GetMapping
    public ResponseEntity<List<TipoVeiculo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoVeiculo> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
