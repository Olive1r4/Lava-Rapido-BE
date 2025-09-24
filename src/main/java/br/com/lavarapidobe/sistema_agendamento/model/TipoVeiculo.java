package br.com.lavarapidobe.sistema_agendamento.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_veiculo")
public class TipoVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex.: Carro, Moto

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
