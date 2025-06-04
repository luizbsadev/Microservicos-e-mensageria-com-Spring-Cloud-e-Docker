package com.udemy.mscartoes.domain;

import java.math.BigDecimal;

public record ResponseListarCartao(Long id, String nome, Bandeira bandeira, BigDecimal renda, BigDecimal limite) {
    public ResponseListarCartao(Cartao cartao){
        this(cartao.getId(), cartao.getNome(), cartao.getBandeira(), cartao.getRenda(), cartao.getLimite());
    }
}
