package com.vaer.demo.springbootrabbitmq.consumer;

import com.vaer.demo.springbootrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @created 22/01/2022 - 22:42 by VAER
 */

@Component
public class OrderConsumer {

    /* class fields */
    //@Value("${queue.name}")
    private final String queueName = "vaer_queue";      //need to be constant

    /* class methods */
    @RabbitListener(queues = queueName)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Successfully receive message from queue: " + orderStatus);
    }
}
