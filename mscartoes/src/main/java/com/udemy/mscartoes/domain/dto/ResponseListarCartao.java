package com.udemy.mscartoes.domain.dto;

import com.udemy.mscartoes.domain.Bandeira;
import com.udemy.mscartoes.domain.Cartao;

import java.math.BigDecimal;

public record ResponseListarCartao(Long id, String nome, Bandeira bandeira, BigDecimal renda, BigDecimal limite) {
    public ResponseListarCartao(Cartao cartao){
        this(cartao.getId(), cartao.getNome(), cartao.getBandeira(), cartao.getRenda(), cartao.getLimite());
    }
}
