package com.lucaspo.costs.repositories;

import com.lucaspo.costs.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
}
