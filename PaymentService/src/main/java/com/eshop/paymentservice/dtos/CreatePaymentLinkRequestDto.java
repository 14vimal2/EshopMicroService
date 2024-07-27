package com.eshop.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreatePaymentLinkRequestDto {
    private String orderId;
    private Long amount;
    private String currency;
    private String description;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Map<String, String> notes;
    private String callbackUrl;


}



