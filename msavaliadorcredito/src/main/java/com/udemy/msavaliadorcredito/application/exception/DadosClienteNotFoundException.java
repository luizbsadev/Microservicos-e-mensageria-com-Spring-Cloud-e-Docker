package com.udemy.msavaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException(){
        super("Dados do cliente não encontrado");
    }
}
