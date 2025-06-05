package com.udemy.mscartoes.domain.dto;

import com.udemy.mscartoes.domain.Bandeira;
import com.udemy.mscartoes.domain.ClienteCartao;

import java.math.BigDecimal;

public record ResponseListarCartaoPorCPF(String nome, Bandeira bandeira, BigDecimal limite) {
     public ResponseListarCartaoPorCPF(ClienteCartao clienteCartao){
        this(clienteCartao.getCartao().getNome(), clienteCartao.getCartao().getBandeira(), clienteCartao.getCartao().getLimite());
    }
}
