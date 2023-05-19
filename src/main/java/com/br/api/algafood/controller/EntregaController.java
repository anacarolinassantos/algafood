package com.br.api.algafood.controller;

import com.br.api.algafood.entity.Entrega;
import com.br.api.algafood.repository.EntregaRepository;
import com.br.api.algafood.service.EntregaService;
import com.br.api.algafood.service.FinalizacaoEntregaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private EntregaService entregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PutMapping("/{entregaId}/finalizacao")
    void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }
    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entrega))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Entrega cadastrar(@Valid @RequestBody Entrega entrega) {
        return entregaService.salvar(entrega);
    }


    @DeleteMapping("{id}")
    public void excluir(@PathVariable Long id) {
        entregaService.excluir(id);
    }
}
