package com.br.api.algafood.service;

import com.br.api.algafood.entity.Cliente;
import com.br.api.algafood.entity.Entrega;
import com.br.api.algafood.enuns.StatusEntrega;
import com.br.api.algafood.exceptions.NegocioException;
import com.br.api.algafood.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;

    private ClienteService clienteService;

    public Entrega buscar(Long id){
        return entregaRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Entrega não encontrado"));
    }

    @Transactional
    public Entrega salvar(Entrega entrega){
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }

    @Transactional
    public void excluir(Long id){
        entregaRepository.deleteById(id);
    }
}
