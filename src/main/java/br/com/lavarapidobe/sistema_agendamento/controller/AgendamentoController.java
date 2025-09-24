package br.com.lavarapidobe.sistema_agendamento.controller;

import br.com.lavarapidobe.sistema_agendamento.model.Agendamento;
import br.com.lavarapidobe.sistema_agendamento.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST: expõe endpoints da API
// Diz ao Spring que essa classe é um controlador REST.
// O retorno dos métodos será convertido automaticamente em JSON (ou outro formato suportado, como XML).
@RestController
// Define o “prefixo” da URL para todos os métodos dessa classe.
@RequestMapping("/agendamentos")
public class AgendamentoController {

    // Controller depende da lógica de negócio que está no Service.
    // O Spring automaticamente injeta o AgendamentoService (graças ao mecanismo de Inversão de Controle - IoC).
    // Assim, o controlador não acessa o banco diretamente, ele pede para o service resolver.
    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service; // Injeção de dependência do serviço
    }

    // Criar um novo agendamento
    // @PostMapping → Associa esse metodo ao verbo HTTP POST.
	// @RequestBody → Converte automaticamente o JSON enviado pelo cliente para um objeto Agendamento.
    // ResponseEntity.status(201) → Retorna 201 Created com o objeto salvo no corpo da resposta.
    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
        return ResponseEntity.status(201).body(service.salvar(agendamento));
    }

    // Listar todos os agendamentos
    // @GetMapping → Associa ao verbo HTTP GET.
	// Retorna a lista de todos os agendamentos como JSON.
    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // Buscar agendamento por ID
    // @GetMapping("/{id}") → URL com parâmetro de caminho (ex: /agendamentos/5).
    // @PathVariable → Pega o valor da URL e coloca no parâmetro id.
    // Usa Optional para verificar se encontrou o agendamento.
    // Se existe → retorna 200 OK com o agendamento.
    // Se não existe → retorna 404 Not Found.
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Excluir agendamento por ID
    // @DeleteMapping("/{id}") → Associa ao verbo HTTP DELETE.
	// Chama service.excluir(id).
    // Retorna 204 No Content (padrão quando algo foi excluído com sucesso, sem corpo na resposta).
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
