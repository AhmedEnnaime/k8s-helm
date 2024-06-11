package com.learning.practice.config;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailRoute extends RouteBuilder {

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttls;

    @Override
    public void configure() throws Exception {
        from("seda:sendEmail")
                .log(LoggingLevel.INFO,"SENDING EMAIL NOTIFICATION")
                .to("smtps://{{spring.mail.host}}:{{spring.mail.port}}?username={{spring.mail.username}}&password={{spring.mail.password}}&mail.smtp.auth=auth&mail.smtp.starttls.enable=starttls")
                .log(LoggingLevel.INFO,"EMAIL NOTIFICATION SENT")
                .stop();
    }
}
