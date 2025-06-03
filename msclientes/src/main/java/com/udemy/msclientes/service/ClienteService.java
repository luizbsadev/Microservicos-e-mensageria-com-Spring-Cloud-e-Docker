package com.udemy.msclientes.service;

import com.udemy.msclientes.domain.Cliente;
import com.udemy.msclientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {
	private final ClienteRepository repository;
	
	@Transactional
	public Optional<Cliente> salvar(Cliente cliente) {
		return Optional.of(repository.save(cliente));
	}

	public Optional<Cliente> getByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
}
