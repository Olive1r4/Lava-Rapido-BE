package br.com.lavarapidobe.sistema_agendamento.service;

import br.com.lavarapidobe.sistema_agendamento.model.Agendamento;
import br.com.lavarapidobe.sistema_agendamento.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Camada de serviço: regras de negócio
@Service
public class  AgendamentoService {

    // Esse atributo vai ser usado dentro da classe para chamar os métodos do repositório (que acessa o banco de dados)
    private final AgendamentoRepository repository;

    // O Spring Boot automaticamente cria um objeto AgendamentoRepository (a implementação gerada em tempo de execução)
    // e injeta aqui quando o Service é inicializado.
    // Esse processo é chamado de Injeção de Dependência (Dependency Injection).
    // Em vez de você criar manualmente com new AgendamentoRepository(),
    // o Spring se encarrega de fornecer a instância correta.
    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository; // Injeção de dependência do repositório
    }

    // Salva um novo agendamento
    public Agendamento salvar(Agendamento agendamento) {
        return repository.save(agendamento);
    }

    // Listar todos os agendamentos
    // O metodo findAll() já vem pronto do JpaRepository.
	// Ele executa um SELECT * FROM agendamentos no banco.
    public List<Agendamento> listar() {
        // Implemente aqui a lógica para combinar os filtros usando o repository
        // Exemplo: chamar métodos do repository conforme os parâmetros não nulos
        return repository.findAll();
    }

    // Metodo com filtros (mantenha se necessário)
    public List<Agendamento> listarComFiltros(
            String nomeUsuario,
            String telefoneUsuario,
            LocalDate dataAgendada,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        // Implementar lógica de filtros aqui
        return repository.findAll();
    }

    // Busca por ID
    // O findById(id) também é herdado de JpaRepository.
    // Ele gera automaticamente um SELECT * FROM agendamentos WHERE id = ?
    public Optional<Agendamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Excluir agendamento por ID
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
