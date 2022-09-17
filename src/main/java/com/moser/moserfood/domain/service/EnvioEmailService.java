package com.moser.moserfood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

/**
 * @author Juliano Moser
 */
public interface EnvioEmailService {

    void enviar(MensagemEmail mensagemEmail);

    @Getter
    @Builder
    class MensagemEmail {

        @Singular("destinatario")
        private Set<String> destinatarios;
        @NonNull
        private String assunto;
        @NonNull
        private String corpo;
        @Singular("variavel")
        private Map<String, Object> variaveis;
    }
}
