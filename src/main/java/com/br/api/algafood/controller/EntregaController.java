package com.br.api.algafood.controller;

import com.br.api.algafood.model.Entrega;
import com.br.api.algafood.repository.EntregaRepository;
import com.br.api.algafood.service.EntregaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaService entregaService;

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
