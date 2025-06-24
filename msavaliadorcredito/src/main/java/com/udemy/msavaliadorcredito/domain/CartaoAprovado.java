package com.udemy.msavaliadorcredito.domain;


import java.math.BigDecimal;

public record CartaoAprovado(String cartao, String bandeira, BigDecimal limiteAprovado) {
     public CartaoAprovado(Cartao cartao, BigDecimal limiteAprovado){
         this(cartao.getNome(), cartao.getBandeira(), limiteAprovado);
     }
}
