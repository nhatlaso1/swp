package org.example.services.interfaces;

import org.example.entities.EmailDetails;

public interface IEmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
