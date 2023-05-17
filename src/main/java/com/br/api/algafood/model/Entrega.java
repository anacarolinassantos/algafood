package com.br.api.algafood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entrega {

    @Id
    private Long id;
    private BigDecimal taxa;
    private LocalDate dataPedido;
    private LocalDate dataFinalizacao;
}
