package com.udemy.mscartoes.applications;

import com.udemy.mscartoes.domain.ClienteCartao;
import com.udemy.mscartoes.domain.dto.RequestCadastroCartao;
import com.udemy.mscartoes.domain.Cartao;
import com.udemy.mscartoes.domain.dto.ResponseListarCartao;
import com.udemy.mscartoes.domain.dto.ResponseListarCartaoPorCPF;
import com.udemy.mscartoes.service.CartoesService;
import com.udemy.mscartoes.service.ClienteCartaoService;
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

    private final CartoesService cartoesService;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    ResponseEntity<HttpStatus> cadastrarCartao(@RequestBody RequestCadastroCartao cadastro){
        Cartao cartao = cartoesService.cadastrarCartao(cadastro.toCartao());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    ResponseEntity<List<ResponseListarCartao>> listarCartaoPorRenda(@Param("renda") Integer renda){
        List<Cartao> listaDeCartoes = cartoesService.listarCartaoPorRenda(BigDecimal.valueOf(renda));
        List<ResponseListarCartao> response = listaDeCartoes
                                                    .stream()
                                                    .map(ResponseListarCartao::new)
                                                    .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "cpf")
    ResponseEntity<List<ResponseListarCartaoPorCPF>> listarCartaoPorCpf(@Param("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.findByCpf(cpf);
        List<ResponseListarCartaoPorCPF> response = lista
                                                        .stream()
                                                        .map(ResponseListarCartaoPorCPF::new)
                                                        .toList();

        return ResponseEntity.ok(response);
    }
}
