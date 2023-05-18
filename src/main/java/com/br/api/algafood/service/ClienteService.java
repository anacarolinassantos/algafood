package com.br.api.algafood.service;

import com.br.api.algafood.exceptions.NegocioException;
import com.br.api.algafood.model.Cliente;
import com.br.api.algafood.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }
    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailExiste = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if(emailExiste){
            throw new NegocioException("Já existe esse e-mail cadastrado no sistema");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id){
        clienteRepository.deleteById(id);
    }
}
