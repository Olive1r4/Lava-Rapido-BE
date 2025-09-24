package br.com.lavarapidobe.sistema_agendamento.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO para criar/receber dados de Agendamento
 * Simplifica a entrada de dados - cliente só precisa enviar IDs
 */
public class AgendamentoRequestDTO {

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Tipo de veículo é obrigatório")
    private Long tipoVeiculoId;

    @NotNull(message = "Tipo de serviço é obrigatório")
    private Long tipoServicoId;

    @NotNull(message = "Modelo é obrigatório")
    private String modelo;

    @NotNull(message = "Placa é obrigatória")
    private String placa;

    @NotNull(message = "Data do agendamento é obrigatória")
    private LocalDate dataAgendada;

    // Construtores
    public AgendamentoRequestDTO() {}

    // Getters e Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getTipoVeiculoId() {
        return tipoVeiculoId;
    }

    public void setTipoVeiculoId(Long tipoVeiculoId) {
        this.tipoVeiculoId = tipoVeiculoId;
    }

    public Long getTipoServicoId() {
        return tipoServicoId;
    }

    public void setTipoServicoId(Long tipoServicoId) {
        this.tipoServicoId = tipoServicoId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }
}
