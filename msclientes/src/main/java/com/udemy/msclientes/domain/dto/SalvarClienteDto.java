package com.udemy.msclientes.domain.dto;

import com.udemy.msclientes.domain.Cliente;

public record SalvarClienteDto(String cpf, String nome, Integer idade) {
    public Cliente criarCliente(){
        return  new Cliente(cpf, nome, idade);
    }
}
