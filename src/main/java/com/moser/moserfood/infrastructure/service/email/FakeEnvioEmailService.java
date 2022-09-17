package com.moser.moserfood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Juliano Moser
 */
@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(MensagemEmail mensagemEmail) {
        String corpo = processarTemplate(mensagemEmail);
        log.info("[FAKE E-EMAIL] para {}/n{}", mensagemEmail.getDestinatarios(), corpo);
    }
}
