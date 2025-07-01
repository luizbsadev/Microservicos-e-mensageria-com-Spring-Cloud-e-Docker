package com.udemy.msavaliadorcredito.domain.dto;

import java.math.BigDecimal;

public record DadosSolicitacaoEmissaoCartaoDTO(Long idCartao, String cpf, String endereco, BigDecimal limiteLiberado) {
}
