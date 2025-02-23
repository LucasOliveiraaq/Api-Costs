package com.lucaspo.costs.service;

import com.lucaspo.costs.model.Categoria;
import com.lucaspo.costs.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria obterCategoria(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
