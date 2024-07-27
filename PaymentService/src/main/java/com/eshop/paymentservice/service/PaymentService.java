package com.eshop.paymentservice.service;

import com.eshop.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.razorpay.RazorpayException;

public interface PaymentService {

    String createPaymentLink(CreatePaymentLinkRequestDto paymentLinkRequestDto) throws RazorpayException;
    String getPaymentStatus(String orderId) throws RazorpayException;
}
