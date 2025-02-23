package com.lucaspo.costs.service;

import com.lucaspo.costs.model.Projeto;
import com.lucaspo.costs.repositories.ProjetoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public boolean deletarProjeto(Integer id) {
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Projeto obterProjeto(Integer id) {
        return projetoRepository.findById(id).orElse(null);
    }

    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto atualizarProjeto(Integer id, Projeto projetoAtualizado) {
        return projetoRepository.findById(id).map(projetoExistente -> {
            if (projetoAtualizado.getNome() != null) {
                projetoExistente.setNome(projetoAtualizado.getNome());
            }
            if (projetoAtualizado.getOrcamento() > 0) {
                projetoExistente.setOrcamento(projetoAtualizado.getOrcamento());
            }
            if (projetoAtualizado.getCategoria() != null) {
                projetoExistente.setCategoria(projetoAtualizado.getCategoria());
            }
            return projetoRepository.save(projetoExistente);
        }).orElse(null);
    }

}
