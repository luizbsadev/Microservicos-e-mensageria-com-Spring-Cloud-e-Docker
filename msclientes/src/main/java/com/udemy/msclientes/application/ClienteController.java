package com.udemy.msclientes.application;

import com.udemy.msclientes.domain.dto.ClienteInteiroDto;
import com.udemy.msclientes.domain.dto.SalvarClienteDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.udemy.msclientes.domain.Cliente;
import com.udemy.msclientes.service.ClienteService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("cliente")
@AllArgsConstructor
public class ClienteController {
	private ClienteService service;

	
	@PostMapping
	ResponseEntity<Cliente> salvarConta(@RequestBody SalvarClienteDto dto) {
		Optional<Cliente> talvezUmCliente = service.salvar(dto.criarCliente());
		if(talvezUmCliente.isPresent()) {
			Cliente cliente = talvezUmCliente.get();
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.query("cpf={cpf}")
					.buildAndExpand(cliente.getCpf())
					.toUri();

			return ResponseEntity.created(uri).build();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	ResponseEntity<ClienteInteiroDto> getByCpf(@RequestParam("cpf") String cpf){
		Optional<Cliente> talvezUmCliente = service.getByCpf(cpf);

        return talvezUmCliente.map(cliente -> ResponseEntity.ok(new ClienteInteiroDto(cliente)))
				.orElseGet(() -> ResponseEntity.notFound().build());

    }

}
