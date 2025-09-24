package br.com.lavarapidobe.sistema_agendamento.mapper;

import br.com.lavarapidobe.sistema_agendamento.dto.AgendamentoRequestDTO;
import br.com.lavarapidobe.sistema_agendamento.dto.AgendamentoResponseDTO;
import br.com.lavarapidobe.sistema_agendamento.dto.UsuarioDTO;
import br.com.lavarapidobe.sistema_agendamento.model.Agendamento;
import br.com.lavarapidobe.sistema_agendamento.model.TipoServico;
import br.com.lavarapidobe.sistema_agendamento.model.TipoVeiculo;
import br.com.lavarapidobe.sistema_agendamento.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * Mapper para converter entre DTOs e Entidades
 * Centraliza a lógica de conversão e cálculos
 */
@Component
public class AgendamentoMapper {

    // Converte DTO para Entidade
    public Agendamento toEntity(AgendamentoRequestDTO dto, Usuario usuario,
                               TipoVeiculo tipoVeiculo, TipoServico tipoServico) {
        Agendamento agendamento = new Agendamento();
        agendamento.setUsuario(usuario);
        agendamento.setTipoVeiculo(tipoVeiculo);
        agendamento.setTipoServico(tipoServico);
        agendamento.setModelo(dto.getModelo());
        agendamento.setPlaca(dto.getPlaca());
        agendamento.setDataAgendada(dto.getDataAgendada());
        return agendamento;
    }

    // Converte Entidade para DTO de resposta
    public AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        // Calcula o valor total (TipoVeiculo + TipoServico)
        Double valorTotal = (agendamento.getTipoVeiculo().getPreco() != null ? agendamento.getTipoVeiculo().getPreco() : 0.0) +
                           (agendamento.getTipoServico().getPreco() != null ? agendamento.getTipoServico().getPreco() : 0.0);

        return new AgendamentoResponseDTO(
            agendamento.getId(),
            agendamento.getUsuario().getNome(),
            agendamento.getUsuario().getTelefone(),
            agendamento.getTipoVeiculo().getNome(),
            agendamento.getTipoServico().getNome(),
            agendamento.getModelo(),
            agendamento.getPlaca(),
            agendamento.getDataAgendada(),
            valorTotal
        );
    }

    // Converte Usuario para DTO
    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getTelefone());
    }

    // Converte DTO para Usuario
    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());
        return usuario;
    }
}
