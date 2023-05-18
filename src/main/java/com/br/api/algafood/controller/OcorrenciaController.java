package com.br.api.algafood.controller;

import com.br.api.algafood.entity.Ocorrencia;
import com.br.api.algafood.repository.OcorrenciaRepository;
import com.br.api.algafood.service.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaService;

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @GetMapping
    public List<Ocorrencia> listar(){
        return ocorrenciaRepository.findAll();
    }

    @PostMapping
    public Ocorrencia registrar(@PathVariable Long entregaId, @Valid @RequestBody Ocorrencia ocorrencia){
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrar(entregaId, ocorrencia.getDescricao());
        return ocorrenciaRegistrada;
    }

}
