package com.eshop.paymentservice.service;

import com.eshop.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentService implements PaymentService {
    private RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(CreatePaymentLinkRequestDto paymentLinkRequestDto) throws RazorpayException {

        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",paymentLinkRequestDto.getAmount()); // 10.01 => 1001 // 99.99 => 9999 // 999 => 99900
            paymentLinkRequest.put("currency",paymentLinkRequestDto.getCurrency());
            paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 15 * 60 * 1000);
            paymentLinkRequest.put("reference_id",paymentLinkRequestDto.getOrderId());
            paymentLinkRequest.put("description","Payment for order no " + paymentLinkRequestDto.getOrderId());


            // Order order = orderService.getOrderDetails(orderId)
            // String cutomerName = order.getUser().getName()
            // String contact = order.getUser().getMobile()

            JSONObject customer = new JSONObject();
            customer.put("name",paymentLinkRequestDto.getCustomerName());
            customer.put("contact",paymentLinkRequestDto.getCustomerPhone());
            customer.put("email",paymentLinkRequestDto.getCustomerEmail());
            paymentLinkRequest.put("customer",customer);

            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("reminder_enable",true);
            JSONObject notes = new JSONObject();

            for(String key : paymentLinkRequestDto.getNotes().keySet()) {
                notes.put(key,paymentLinkRequestDto.getNotes().get(key));
            }

//            notes.put("Order Items","1. iPhone 15 Pro Max");
            paymentLinkRequest.put("notes",notes);
            paymentLinkRequest.put("callback_url",paymentLinkRequestDto.getCallbackUrl());
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url");
        } catch (Exception e) {
            // go to the DB
            // get the old URL
            return "";
        }

    }

    @Override
    public String getPaymentStatus(String paymentId) {
        // Go to DB
        // check if the status of payment in DB
        // if no:
        //  call the payment gateway to check status ofd payment
        //   update that status into its own DB
        // return the status
        return null;
    }
}
