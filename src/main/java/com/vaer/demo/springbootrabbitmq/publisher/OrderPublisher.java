package com.vaer.demo.springbootrabbitmq.publisher;

import com.vaer.demo.springbootrabbitmq.config.MessagingConfig;
import com.vaer.demo.springbootrabbitmq.dto.Order;
import com.vaer.demo.springbootrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @created 22/01/2022 - 21:30 by VAER
 */

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    /* class fields */
    private RabbitTemplate rabbitTemplate;

    /* class fields */
    @Value("${queue.name}")
    private String queueName;

    @Value("${exchange.name}")
    private String exchangeName;

    @Value("${routingkey.name}")
    private String routingKeyName;

    /* constructors */
    @Autowired
    public OrderPublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    /* class methods */
    @PostMapping("/{restaurantName}")
    public OrderStatus bookingOrder(@RequestBody Order order, @PathVariable String restaurantName){

        //setting order id;
        order.setOrderId(UUID.randomUUID().toString());

        //work with restaurantservice
        //work with paymentservice

        //send respond about booking order
        OrderStatus orderStatus = new OrderStatus(order, "PROCCESS", "Order placed successfuly to " + restaurantName);

        //We can put the response to RabbitMQ and do not wait until restaurantservice and paymentservice finished
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, orderStatus);

        return orderStatus;
    }
}
