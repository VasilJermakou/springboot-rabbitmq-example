package com.vaer.demo.springbootrabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @created 22/01/2022 - 21:25 by VAER
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    /* class fields */
    private String orderId;
    private String name;
    private int quantity;
    private double price;
}
