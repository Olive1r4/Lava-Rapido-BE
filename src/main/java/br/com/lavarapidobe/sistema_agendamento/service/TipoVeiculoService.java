package br.com.lavarapidobe.sistema_agendamento.service;

import br.com.lavarapidobe.sistema_agendamento.model.TipoVeiculo;
import br.com.lavarapidobe.sistema_agendamento.repository.TipoVeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVeiculoService {

    private final TipoVeiculoRepository repository;


    public TipoVeiculoService(TipoVeiculoRepository repository) {
        this.repository = repository;
    }

    public TipoVeiculo salvar(TipoVeiculo tipoVeiculo) {
        return repository.save(tipoVeiculo);
    }

    public List<TipoVeiculo> listar() {
        return repository.findAll();
    }

    public Optional<TipoVeiculo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
