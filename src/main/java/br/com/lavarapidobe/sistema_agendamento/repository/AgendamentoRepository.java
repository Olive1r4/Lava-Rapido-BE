package br.com.lavarapidobe.sistema_agendamento.repository;

import br.com.lavarapidobe.sistema_agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface para acesso aos dados, Spring Data JPA cria métodos prontos
// Define uma interface, ou seja, uma espécie de “contrato” que descreve métodos que podem ser usados
// mas sem implementar nada manualmente.
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
