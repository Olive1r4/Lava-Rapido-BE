package br.com.lavarapidobe.sistema_agendamento.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RestController;
@Entity
@Table(name = "tipo_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do tipo de serviço

    private String nome; // Nome do tipo de serviço (Ex.: Lavagem Simples, Lavagem Completa)
    private Double preco; // Preço do serviço

    public TipoServico() {
    }
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
