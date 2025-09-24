package br.com.lavarapidobe.sistema_agendamento.model;

import jakarta.persistence.*;

import java.time.LocalDate;

// Entidade que representa a tabela "agendamentos" no banco de dados
// Entity Indica que a classe é uma entidade JPA, ou seja, ela representa uma tabela no banco de dados.
// O JPA vai mapear os atributos da classe para colunas da tabela.
// Sem o @Entity, o Spring Data JPA não consegue criar ou gerenciar a tabela.
@Entity
// @Table não é obrigatório, o JPA cria a tabela com o mesmo nome da classe
// Nesse caso Estamos espefificando que o nome da tabela será "agendamentos"
@Table(name="agendamentos")
public class Agendamento {
    // India que  o atributo "id" sera a chave primária.
    // O valor do id será gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_veiculo_id") // Chave estrangeira para
    private TipoVeiculo tipoVeiculo; // Ex.: Carro, Moto
    private String modelo;      // Modelo do veículo
    private String placa;       // Placa do veículo
    private String tipoServico; // Tipo de serviço (Lavagem, Polimento)
    private Double valor;       // Valor do serviço
    private LocalDate dataAgendada; // Data escolhida pelo cliente

    // Getters e Setters para cada campo (necessário para JPA)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
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

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }
}
