package com.udemy.msavaliadorcredito.domain.dto;

import com.udemy.msavaliadorcredito.domain.CartaoAprovado;

import java.util.List;

public record RetornoAvaliacaoClienteDTO(List<CartaoAprovado> cartoes) { }
