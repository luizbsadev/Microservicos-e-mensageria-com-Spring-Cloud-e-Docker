package com.udemy.mscartoes.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.mscartoes.domain.Cartao;
import com.udemy.mscartoes.domain.ClienteCartao;
import com.udemy.mscartoes.domain.dto.DadosSolicitacaoEmissaoCartaoDTO;
import com.udemy.mscartoes.repository.CartoesRepository;
import com.udemy.mscartoes.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {
    private final ClienteCartaoRepository clienteCartaoRepository;
    private final CartoesRepository cartoesRepository;


    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload){
        try{
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartaoDTO dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoDTO.class);
            Cartao cartao = cartoesRepository.getReferenceById(dados.idCartao());
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.cpf());
            clienteCartao.setLimite(dados.limiteLiberado());

            clienteCartaoRepository.save(clienteCartao);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
