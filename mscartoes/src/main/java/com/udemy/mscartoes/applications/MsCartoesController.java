package com.udemy.mscartoes.applications;

import com.udemy.mscartoes.domain.dto.RequestCadastroCartao;
import com.udemy.mscartoes.domain.Cartao;
import com.udemy.mscartoes.domain.dto.ResponseListarCartao;
import com.udemy.mscartoes.service.CartoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class MsCartoesController {

    private final CartoesService service;

    @PostMapping
    ResponseEntity<HttpStatus> cadastrarCartao(@RequestBody RequestCadastroCartao cadastro){
        Cartao cartao = service.cadastrarCartao(cadastro.toCartao());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    ResponseEntity<List<ResponseListarCartao>> listarCartaoPorRenda(@Param("renda") Integer renda){
        List<Cartao> listaDeCartoes = service.listarCartaoPorRenda(BigDecimal.valueOf(renda));
        List<ResponseListarCartao> response = listaDeCartoes
                                                    .stream()
                                                    .map(ResponseListarCartao::new)
                                                    .toList();

        return ResponseEntity.ok(response);
    }
}
