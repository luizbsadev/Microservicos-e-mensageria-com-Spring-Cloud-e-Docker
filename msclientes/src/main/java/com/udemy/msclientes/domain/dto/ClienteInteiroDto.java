package com.udemy.msclientes.domain.dto;

import com.udemy.msclientes.domain.Cliente;

public record ClienteInteiroDto(Long id, String cpf, String nome, Integer idade) {
    public ClienteInteiroDto(Cliente cliente){
        this(cliente.getId(), cliente.getCpf(), cliente.getNome(), cliente.getIdade());
    }
}
