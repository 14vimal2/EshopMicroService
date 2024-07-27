package com.eshop.paymentservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long id;
    private Long userId;
    private Long amount;
}
