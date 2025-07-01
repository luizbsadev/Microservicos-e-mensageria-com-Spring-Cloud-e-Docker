package com.udemy.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    String emissaoCartoesFila;

    public Queue listaEmissao(){
        return new Queue(emissaoCartoesFila);
    }
}
