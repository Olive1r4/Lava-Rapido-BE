package br.com.lavarapidobe.sistema_agendamento.repository;

import br.com.lavarapidobe.sistema_agendamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
