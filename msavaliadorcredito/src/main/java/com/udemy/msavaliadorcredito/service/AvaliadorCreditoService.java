package com.udemy.msavaliadorcredito.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.udemy.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.udemy.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import com.udemy.msavaliadorcredito.domain.*;
import com.udemy.msavaliadorcredito.domain.CartaoAprovado;
import com.udemy.msavaliadorcredito.domain.dto.DadosSolicitacaoEmissaoCartaoDTO;
import com.udemy.msavaliadorcredito.domain.dto.ProtocoloSolicitacaoCartaoDTO;
import com.udemy.msavaliadorcredito.domain.dto.RetornoAvaliacaoClienteDTO;
import com.udemy.msavaliadorcredito.domain.dto.SituacaoClienteDTO;
import com.udemy.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.udemy.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.udemy.msavaliadorcredito.infra.mqrabbit.EmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CartoesResourceClient cartoesClient;
    private final EmissaoCartaoPublisher publisher;

    public SituacaoClienteDTO obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return new SituacaoClienteDTO(dadosClienteResponse.getBody(), cartoesResponse.getBody());
        }catch(FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status)
                throw new DadosClienteNotFoundException();
            else
                throw new ErroComunicacaoMicroservicesException(e.getMessage(), e.status());

        }


    }

    public RetornoAvaliacaoClienteDTO realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartoesRendaAteh(renda);


            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovado> listaCartoesAprovados = cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimite();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                return new CartaoAprovado(cartao, limiteAprovado);
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoClienteDTO(listaCartoesAprovados);
        }catch(FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status)
                throw new DadosClienteNotFoundException();
            else
                throw new ErroComunicacaoMicroservicesException(e.getMessage(), e.status());

        }
    }

    public ProtocoloSolicitacaoCartaoDTO solicitarCartao(DadosSolicitacaoEmissaoCartaoDTO dados) {
        try{
            publisher.solicitarCartao(dados);
            String protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartaoDTO(protocolo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
