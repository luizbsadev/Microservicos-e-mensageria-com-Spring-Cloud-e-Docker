package com.udemy.msavaliadorcredito.domain.dto;



import com.udemy.msavaliadorcredito.domain.CartaoCliente;
import com.udemy.msavaliadorcredito.domain.DadosCliente;

import java.util.List;


public record SituacaoClienteDTO(DadosCliente cliente, List<CartaoCliente> cartoes) {

}
