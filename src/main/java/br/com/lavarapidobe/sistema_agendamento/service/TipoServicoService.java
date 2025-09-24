package br.com.lavarapidobe.sistema_agendamento.service;

import br.com.lavarapidobe.sistema_agendamento.model.TipoServico;
import br.com.lavarapidobe.sistema_agendamento.repository.TipoServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServicoService {

    private final TipoServicoRepository repository;

    public TipoServicoService(TipoServicoRepository repository) {
        this.repository = repository;
    }

    public TipoServico salvar(TipoServico tipoServico) {
        return repository.save(tipoServico);
    }

    public List<TipoServico> listar() {
        return repository.findAll();
    }

    public Optional<TipoServico> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
