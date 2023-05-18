package com.br.api.algafood.service;

import com.br.api.algafood.enuns.StatusEntrega;
import com.br.api.algafood.exceptions.NegocioException;
import com.br.api.algafood.model.Cliente;
import com.br.api.algafood.model.Entrega;
import com.br.api.algafood.repository.ClienteRepository;
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
