package com.eshop.emailservice.consumers;


import com.eshop.emailservice.dtos.SendEmailEventDto;
import com.eshop.emailservice.utils.EmailUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailEventConsumer {

    public ObjectMapper objectMapper;

    @Autowired
    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void handleSendEmailEvent(String message) {

        SendEmailEventDto eventDto = objectMapper.convertValue(message, SendEmailEventDto.class);

        String toEmail = eventDto.getTo();
        String subject = eventDto.getSubject();
        String body = eventDto.getBody();

        String fromEmail = "asldfkasdf@gmail.com";
        final String password = "adfljkhasdfjk"; // correct password for gmail id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        EmailUtil.sendEmail(session, toEmail,subject, body);



    }
}
