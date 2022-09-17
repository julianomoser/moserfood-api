package com.moser.moserfood.infrastructure.service.email;

import com.moser.moserfood.core.email.EmailProperties;
import com.moser.moserfood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Juliano Moser
 */
@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void enviar(MensagemEmail mensagemEmail) {
        try {
            MimeMessage mimeMessage = createMimeMessage(mensagemEmail);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não possível enviar e-maiil", e);
        }
    }

    protected MimeMessage createMimeMessage(MensagemEmail mensagemEmail) throws MessagingException {
        String corpo = processarTemplate(mensagemEmail);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getRemetente());
        helper.setTo(mensagemEmail.getDestinatarios().toArray(new String[0]));
        helper.setSubject(mensagemEmail.getAssunto());
        helper.setText(corpo, true);
        return mimeMessage;
    }

    protected String processarTemplate(MensagemEmail mensagemEmail) {
        try {
            Template template = freeMarkerConfig.getTemplate(mensagemEmail.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    template, mensagemEmail.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não possível montar o template do e-maiil", e);
        }
    }
}
