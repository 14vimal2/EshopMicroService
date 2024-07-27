package com.eshop.userservice.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailEventDto implements Serializable {
    private String to;
    private String from;
    private String subject;
    private String body;

}
