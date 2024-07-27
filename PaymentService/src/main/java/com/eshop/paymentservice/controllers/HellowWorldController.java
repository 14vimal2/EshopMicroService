package com.eshop.paymentservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {
    @GetMapping()
    public String helloWorld() {
        return "Hello World!";
    }

}
