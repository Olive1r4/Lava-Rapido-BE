package br.com.lavarapidobe.sistema_agendamento.dto;

import java.time.LocalDate;

/**
 * DTO para resposta de Agendamento
 * Retorna dados completos e calculados, sem expor estruturas internas do JPA
 */
public class AgendamentoResponseDTO {

    private Long id;
    private String nomeUsuario;
    private String telefoneUsuario;
    private String tipoVeiculo;
    private String tipoServico;
    private String modelo;
    private String placa;
    private LocalDate dataAgendada;
    private Double valorTotal; // Calculado automaticamente
    private String status; // Futuro: Pendente, Confirmado, etc.

    // Construtores
    public AgendamentoResponseDTO() {}

    public AgendamentoResponseDTO(Long id, String nomeUsuario, String telefoneUsuario,
                                 String tipoVeiculo, String tipoServico, String modelo,
                                 String placa, LocalDate dataAgendada, Double valorTotal) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.tipoVeiculo = tipoVeiculo;
        this.tipoServico = tipoServico;
        this.modelo = modelo;
        this.placa = placa;
        this.dataAgendada = dataAgendada;
        this.valorTotal = valorTotal;
        this.status = "Pendente"; // Valor padrão
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
