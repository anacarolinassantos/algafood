package com.br.api.algafood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Destinatario {

    @Id
    private Long id;
    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
