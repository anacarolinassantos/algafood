package com.br.api.algafood.service;

import com.br.api.algafood.entity.Entrega;
import com.br.api.algafood.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaService entregaService;

    private EntregaRepository entregaRepository;

    @Transactional
    void finalizar(Long entregaId){
        Entrega entrega = entregaService.buscar(entregaId);
        entregaRepository.save(entrega);

    }
}
