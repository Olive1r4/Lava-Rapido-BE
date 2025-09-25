package br.com.lavarapidobe.sistema_agendamento.repository;

import br.com.lavarapidobe.sistema_agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Interface para acesso aos dados, Spring Data JPA cria métodos prontos
// Define uma interface, ou seja, uma espécie de “contrato” que descreve métodos que podem ser usados
// mas sem implementar nada manualmente.
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByUsuarioNomeContainingIgnoreCase(String nomeUsuario);
    List<Agendamento> findByUsuarioTelefone(String telefoneUsuario);
    List<Agendamento> findByDataAgendada(java.time.LocalDate dataAgendada);
    List<Agendamento> findByDataAgendadaBetween(java.time.LocalDate dataInicio, java.time.LocalDate dataFim);
}
