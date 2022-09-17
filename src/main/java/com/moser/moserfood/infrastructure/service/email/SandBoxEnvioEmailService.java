package com.moser.moserfood.infrastructure.service.email;

import com.moser.moserfood.core.email.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Juliano Moser
 */
public class SandBoxEnvioEmailService extends SmtpEnvioEmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Override
    protected MimeMessage createMimeMessage(MensagemEmail mensagemEmail) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(mensagemEmail);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());
        return mimeMessage;
    }
}
