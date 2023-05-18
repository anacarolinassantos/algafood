package com.br.api.algafood.service;

import com.br.api.algafood.entity.Entrega;
import com.br.api.algafood.entity.Ocorrencia;
import com.br.api.algafood.exceptions.EntidadeNaoEncontradaException;
import com.br.api.algafood.exceptions.NegocioException;
import com.br.api.algafood.repository.EntregaRepository;
import com.br.api.algafood.repository.OcorrenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    private OcorrenciaRepository ocorrenciaRepository;

    private EntregaRepository entregaRepository;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
        entrega.adicionarOcorrencia(descricao);

        return entrega.adicionarOcorrencia(descricao);

    }
}
