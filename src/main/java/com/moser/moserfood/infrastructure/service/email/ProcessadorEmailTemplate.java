package com.moser.moserfood.infrastructure.service.email;

import com.moser.moserfood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * @author Juliano Moser
 */
@Component
public class ProcessadorEmailTemplate {

    @Autowired
    private Configuration freeMarkerConfig;

    protected String processarTemplate(EnvioEmailService.MensagemEmail mensagemEmail) {
        try {
            Template template = freeMarkerConfig.getTemplate(mensagemEmail.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    template, mensagemEmail.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não possível montar o template do e-maiil", e);
        }
    }
}
