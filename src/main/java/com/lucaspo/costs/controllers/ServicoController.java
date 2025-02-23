package com.lucaspo.costs.controllers;

import com.lucaspo.costs.dto.ApiResponse;
import com.lucaspo.costs.model.Projeto;
import com.lucaspo.costs.model.Servico;
import com.lucaspo.costs.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    private final ServicoService  servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<Servico> listarServicos() {
        return servicoService.listarServicos();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Servico>> criarServico(@RequestBody Servico servico, @RequestParam Integer projetoId) {

        Servico novoServico = servicoService.salvarServico(servico, projetoId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Serviço criado com sucesso!", "success", novoServico));
    }

//    @GetMapping("/{id}")
//    public Servico obterServico(@PathVariable Integer id) {
//        return servicoService.obterServico(id);
//    }

    @PutMapping("/{id}")
    public Servico atualizarServico(@PathVariable Integer id, @RequestBody Servico servicoAtualizado) {
        return servicoService.atualizarServico(id, servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarServico(@PathVariable Integer id) {
        servicoService.deletarServico(id);
    }
}
