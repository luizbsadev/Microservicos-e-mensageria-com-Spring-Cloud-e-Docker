package com.udemy.mscartoes.service;

import com.udemy.mscartoes.domain.Cartao;
import com.udemy.mscartoes.repository.CartoesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartoesService {

    private final CartoesRepository repository;

    public Cartao cadastrarCartao(Cartao cartao){
        repository.save(cartao);
        return cartao;
    }

    public List<Cartao> listarCartaoPorRenda(BigDecimal renda) {
        return repository.findByRendaLessThan(renda);
    }
}
