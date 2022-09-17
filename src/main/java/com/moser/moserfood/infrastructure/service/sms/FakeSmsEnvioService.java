package com.moser.moserfood.infrastructure.service.sms;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Juliano Moser
 */
@Slf4j
public class FakeSmsEnvioService extends TwilioSmsEnvioService {
    @Override
    public void sendSms(MensagemSms mensagem) {

        log.info("[FAKE SMS] Sms enviado \n{}", mensagem.getMensagem());
    }

}
