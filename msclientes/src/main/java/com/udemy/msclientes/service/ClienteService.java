package com.udemy.msclientes.service;

import com.udemy.msclientes.domain.Cliente;
import com.udemy.msclientes.repository.ClienteRepository;

public class ClienteService {
	private final ClienteRepository repository;

	public ClienteService(ClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	public Cliente salvar(String cpf, String nome, Integer idade) {
		return repository.save(new Cliente(cpf, nome, idade));
	}
	
}
