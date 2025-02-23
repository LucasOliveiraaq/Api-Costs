package com.lucaspo.costs.controllers;

import com.lucaspo.costs.dto.ApiResponse;
import com.lucaspo.costs.model.Projeto;
import com.lucaspo.costs.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Projeto>>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarProjetos();
        return ResponseEntity.ok(new ApiResponse<>("Lista de projetos obtida com sucesso", "success", projetos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Projeto>> criarProjeto(@RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.salvarProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Projeto criado com sucesso!", "success", novoProjeto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Projeto>> obterProjeto(@PathVariable Integer id) {
        Projeto projeto = projetoService.obterProjeto(id);
        if (projeto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Projeto não encontrado", "erro"));
        }
        return ResponseEntity.ok(new ApiResponse<>("Projeto encontrado com sucesso", "success", projeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletarProjeto(@PathVariable Integer id) {
        boolean deletado = projetoService.deletarProjeto(id);
        if (!deletado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Projeto não encontrado", "erro"));
        }
        return ResponseEntity.ok(new ApiResponse<>("Projeto deletado com sucesso", "sucesso"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Projeto>> atualizarProjeto(@PathVariable Integer id, @RequestBody Projeto projetoAtualizado) {
        if (projetoAtualizado.getOrcamento() < projetoAtualizado.getCusto()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("O orçamento não pode ser menor que o custo do projeto!", "erro"));
        }

        Projeto projeto = projetoService.atualizarProjeto(id, projetoAtualizado);
        if (projeto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Projeto não encontrado", "erro"));
        }
        return ResponseEntity.ok(new ApiResponse<>("Projeto atualizado com sucesso", "success", projeto));
    }
}
