package com.udemy.msavaliadorcredito.application;

import com.udemy.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.udemy.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import com.udemy.msavaliadorcredito.domain.dto.*;
import com.udemy.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@AllArgsConstructor
public class MsAvaliadorCreditoController {
    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf)  {
        try {
            SituacaoClienteDTO situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).build();
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoDTO dados ){
        try {
            RetornoAvaliacaoClienteDTO retornoAvaliacaoCliente = avaliadorCreditoService
                    .realizarAvaliacao(dados.cpf(), dados.renda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (ErroComunicacaoMicroservicesException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).build();
        }
    }
    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitaCartao(@RequestBody DadosSolicitacaoEmissaoCartaoDTO dados){
        try{
            ProtocoloSolicitacaoCartaoDTO protocolo = avaliadorCreditoService.solicitarCartao(dados);
            return ResponseEntity.ok(protocolo);
        }catch (Exception e ){
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
