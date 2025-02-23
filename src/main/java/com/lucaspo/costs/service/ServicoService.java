package com.lucaspo.costs.service;

import com.lucaspo.costs.model.Projeto;
import com.lucaspo.costs.model.Servico;
import com.lucaspo.costs.repositories.ProjetoRepository;
import com.lucaspo.costs.repositories.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ProjetoRepository projetoRepository;

    public ServicoService(ServicoRepository servicoRepository, ProjetoRepository projetoRepository) {
        this.servicoRepository = servicoRepository;
        this.projetoRepository = projetoRepository;
    }

    public Servico salvarServico(Servico servico, Integer projetoId) {
        // Verificar se o projeto existe
        Optional<Projeto> projetoOptional = projetoRepository.findById(projetoId);
        if (!projetoOptional.isPresent()) {
            throw new IllegalArgumentException("Projeto não encontrado!");
        }

        Projeto projeto = projetoOptional.get();
        servico.setProjeto(projeto);
        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico atualizarServico(Integer id, Servico servicoAtualizado) {
        Servico servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!"));

        servicoExistente.setNome(servicoAtualizado.getNome());
        servicoExistente.setDescricao(servicoAtualizado.getDescricao());
        servicoExistente.setCusto(servicoAtualizado.getCusto());

        return servicoRepository.save(servicoExistente);
    }

    public void deletarServico(Integer id) {
        Servico servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!"));

        servicoRepository.delete(servicoExistente);
    }
}
