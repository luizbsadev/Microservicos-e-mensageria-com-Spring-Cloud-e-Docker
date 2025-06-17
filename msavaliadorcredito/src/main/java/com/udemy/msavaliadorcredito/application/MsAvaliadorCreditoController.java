package com.udemy.msavaliadorcredito.application;

import com.udemy.msavaliadorcredito.domain.SituacaoCliente;
import com.udemy.msavaliadorcredito.domain.dto.DadosAvaliacaoDTO;
import com.udemy.msavaliadorcredito.domain.dto.RetornoAvaliacaoClienteDTO;
import com.udemy.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avalicoes-credito")
@AllArgsConstructor
public class MsAvaliadorCreditoController {
    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);

    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoDTO dados ){
            RetornoAvaliacaoClienteDTO retornoAvaliacaoCliente = avaliadorCreditoService
                    .realizarAvaliacao(dados.cpf(), dados.renda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);

    }
}
