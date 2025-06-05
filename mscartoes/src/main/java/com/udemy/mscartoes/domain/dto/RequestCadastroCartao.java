package com.udemy.mscartoes.domain.dto;

import com.udemy.mscartoes.domain.Bandeira;
import com.udemy.mscartoes.domain.Cartao;

import java.math.BigDecimal;

public record RequestCadastroCartao(String nome, Bandeira bandeira, BigDecimal renda, BigDecimal limite){
    public Cartao toCartao(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
