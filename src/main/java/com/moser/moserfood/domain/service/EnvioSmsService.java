package com.moser.moserfood.domain.service;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Juliano Moser
 */
public interface EnvioSmsService {

    void sendSms(MensagemSms mensagem);

    @Getter
    @Builder
    class MensagemSms {
        private String mensagem;
    }
}
