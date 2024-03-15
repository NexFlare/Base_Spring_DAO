package com.nexflare.blog.util;

import lombok.NoArgsConstructor;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class EmailModule {

    public void sendEmail(String to, String subject, String message) {
        new Thread(() -> {
            try {
                Email email = new SimpleEmail();
                email.setHostName("smtp-mail.outlook.com");
                email.setSmtpPort(587);
                email.setStartTLSEnabled(true);
                email.setAuthenticator(new DefaultAuthenticator("", ""));
                email.setFrom("quickfixteam@outlook.com");
                email.setSubject(subject);
                email.setMsg(message);
                email.addTo(to);
                email.send();
            } catch(EmailException e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

}
