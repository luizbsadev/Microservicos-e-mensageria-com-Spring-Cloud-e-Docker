package com.udemy.mscartoes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    @Enumerated
    private Bandeira bandeira;
    @Column
    private BigDecimal renda;
    @Column
    private BigDecimal limite;

    public Cartao(String nome, Bandeira bandeira, BigDecimal renda, BigDecimal limite) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limite = limite;
    }
}
