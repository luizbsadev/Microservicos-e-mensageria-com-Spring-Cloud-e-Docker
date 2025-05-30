package com.udemy.msclientes.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.msclientes.domain.Cliente;
import com.udemy.msclientes.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {
	private ClienteService service;

	@GetMapping
	String getConta() {
		return "Hello World";
	}
	
	@PostMapping
	Cliente SalvarConta(String cpf, String nome, Integer idade) {
		Cliente cliente = service.salvar(cpf, nome, idade);
		return cliente;
	}
}
