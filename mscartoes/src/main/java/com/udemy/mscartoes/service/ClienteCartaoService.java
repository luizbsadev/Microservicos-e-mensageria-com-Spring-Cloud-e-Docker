package com.udemy.mscartoes.service;

import com.udemy.mscartoes.domain.ClienteCartao;
import com.udemy.mscartoes.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
