package com.vaer.demo.springbootrabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @created 22/01/2022 - 21:28 by VAER
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatus {

    /* class fields */
    private Order order;
    private String status;  //progress, completed
    private String message;
}
