package br.com.lavarapidobe.sistema_agendamento.repository;

import br.com.lavarapidobe.sistema_agendamento.model.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVeiculoRepository extends JpaRepository<TipoVeiculo, Long> {
}
