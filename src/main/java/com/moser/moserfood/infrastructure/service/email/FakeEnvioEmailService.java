package com.moser.moserfood.infrastructure.service.email;

import com.moser.moserfood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Juliano Moser
 */
@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {

    @Autowired
    private ProcessadorEmailTemplate processadorEmailTemplate;

    @Override
    public void enviar(MensagemEmail mensagemEmail) {
        String corpo = processadorEmailTemplate.processarTemplate(mensagemEmail);
        log.info("[FAKE E-EMAIL] para {}/n{}", mensagemEmail.getDestinatarios(), corpo);
    }
}
