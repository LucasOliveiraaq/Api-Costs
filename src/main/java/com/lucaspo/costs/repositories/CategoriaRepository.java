package com.lucaspo.costs.repositories;

import com.lucaspo.costs.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
