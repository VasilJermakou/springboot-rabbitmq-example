package com.vaer.demo.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @created 22/01/2022 - 20:11 by VAER
 */

@Configuration
public class MessagingConfig {

    /* class fields */
    @Value("${queue.name}")
    private String queueName;

    @Value("${exchange.name}")
    private String exchangeName;

    @Value("${routingkey.name}")
    private String routingKeyName;

    /**
     * Creating queue
     * */
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    /**
     * Creating exchange
     * */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(exchangeName);
    }

    /**
     * Binding queue with exchange using routingKey
     * */
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKeyName);
    }

    /**
     * Helps us to work with object instead of json string
     * */
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Add RabbitMQ template
     * in this template we can published the event, then to queue and then consumered it
     * */
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
