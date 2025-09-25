package br.com.lavarapidobe.sistema_agendamento.controller;

import br.com.lavarapidobe.sistema_agendamento.dto.AgendamentoRequestDTO;
import br.com.lavarapidobe.sistema_agendamento.dto.AgendamentoResponseDTO;
import br.com.lavarapidobe.sistema_agendamento.mapper.AgendamentoMapper;
import br.com.lavarapidobe.sistema_agendamento.model.Agendamento;
import br.com.lavarapidobe.sistema_agendamento.model.TipoServico;
import br.com.lavarapidobe.sistema_agendamento.model.TipoVeiculo;
import br.com.lavarapidobe.sistema_agendamento.model.Usuario;
import br.com.lavarapidobe.sistema_agendamento.service.AgendamentoService;
import br.com.lavarapidobe.sistema_agendamento.service.TipoServicoService;
import br.com.lavarapidobe.sistema_agendamento.service.TipoVeiculoService;
import br.com.lavarapidobe.sistema_agendamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// Controlador REST: expõe endpoints da API
// Diz ao Spring que essa classe é um controlador REST.
// O retorno dos métodos será convertido automaticamente em JSON (ou outro formato suportado, como XML).
@RestController
// Define o "prefixo" da URL para todos os métodos dessa classe.
@RequestMapping("/agendamentos")
public class AgendamentoController {

    // Controller depende da lógica de negócio que está no Service.
    // O Spring automaticamente injeta o AgendamentoService (graças ao mecanismo de Inversão de Controle - IoC).
    // Assim, o controlador não acessa o banco diretamente, ele pede para o service resolver.
    private final AgendamentoService service;
    private final UsuarioService usuarioService;
    private final TipoVeiculoService tipoVeiculoService;
    private final TipoServicoService tipoServicoService;
    private final AgendamentoMapper mapper;

    public AgendamentoController(AgendamentoService service,
                               UsuarioService usuarioService,
                               TipoVeiculoService tipoVeiculoService,
                               TipoServicoService tipoServicoService,
                               AgendamentoMapper mapper) {
        this.service = service; // Injeção de dependência do serviço
        this.usuarioService = usuarioService;
        this.tipoVeiculoService = tipoVeiculoService;
        this.tipoServicoService = tipoServicoService;
        this.mapper = mapper;
    }

    // Criar um novo agendamento com DTOs
    // @PostMapping → Associa esse metodo ao verbo HTTP POST.
    // @RequestBody → Converte automaticamente o JSON enviado pelo cliente para um objeto DTO.
    // ResponseEntity.status(201) → Retorna 201 Created com o objeto salvo no corpo da resposta.
    /**
     * NOVO - Usando DTOs:
     * Cliente envia: {"usuarioId": 1, "tipoVeiculoId": 1, "tipoServicoId": 1, ...}
     * API retorna dados completos com valor calculado automaticamente
     */
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar(@Valid @RequestBody AgendamentoRequestDTO dto) {
        // Busca as entidades relacionadas pelos IDs
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        TipoVeiculo tipoVeiculo = tipoVeiculoService.buscarPorId(dto.getTipoVeiculoId())
            .orElseThrow(() -> new RuntimeException("Tipo de veículo não encontrado"));

        TipoServico tipoServico = tipoServicoService.buscarPorId(dto.getTipoServicoId())
            .orElseThrow(() -> new RuntimeException("Tipo de serviço não encontrado"));

        // Converte DTO para entidade
        Agendamento agendamento = mapper.toEntity(dto, usuario, tipoVeiculo, tipoServico);

        // Salva
        Agendamento agendamentoSalvo = service.salvar(agendamento);

        // Converte para DTO de resposta (com valor calculado automaticamente)
        AgendamentoResponseDTO responseDTO = mapper.toResponseDTO(agendamentoSalvo);

        return ResponseEntity.status(201).body(responseDTO);
    }

    // Listar todos os agendamentos com dados completos
    // @GetMapping → Associa ao verbo HTTP GET.
    // Retorna a lista de todos os agendamentos como JSON com dados enriquecidos.
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar(
            @RequestParam(required = false) String nomeUsuario,
            @RequestParam(required = false) String telefoneUsuario,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAgendada,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        List<AgendamentoResponseDTO> agendamentosDTO = service.listarComFiltros(
                        nomeUsuario, telefoneUsuario, dataAgendada, dataInicio, dataFim
                ).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentosDTO);
    }

    // Buscar agendamento por ID com dados completos
    // @GetMapping("/{id}") → URL com parâmetro de caminho (ex: /agendamentos/5).
    // @PathVariable → Pega o valor da URL e coloca no parâmetro id.
    // Retorna DTO com dados completos e valor calculado.
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(mapper::toResponseDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
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
