package com.udemy.msavaliadorcredito.infra.mqrabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.msavaliadorcredito.domain.dto.DadosSolicitacaoEmissaoCartaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartao;

    public void solicitarCartao(DadosSolicitacaoEmissaoCartaoDTO dados) throws JsonProcessingException {
        String json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartao.getName(), json);
    }

    private String convertIntoJson(DadosSolicitacaoEmissaoCartaoDTO dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);

    }

}
