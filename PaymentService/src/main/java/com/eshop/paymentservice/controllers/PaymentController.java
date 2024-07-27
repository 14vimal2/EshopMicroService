package com.eshop.paymentservice.controllers;

import com.eshop.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.eshop.paymentservice.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto requestDto) throws RazorpayException {
        return paymentService.createPaymentLink(requestDto);
    }


    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Map<String, String> webhook) throws RazorpayException {
        System.out.println(webhook);
    }
}
