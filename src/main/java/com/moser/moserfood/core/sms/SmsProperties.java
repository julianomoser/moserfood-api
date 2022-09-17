package com.moser.moserfood.core.sms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Juliano Moser
 */
@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("moserfood.sms")
public class SmsProperties {

    private Twilio twilio = new Twilio();
    private Implementacao impl = Implementacao.FAKE;

    public enum Implementacao {
        FAKE, TWILIO
    }

    @Getter
    @Setter
    public static class Twilio {
        private String sid;
        private String key;
        private String phoneFrom;
        private String phoneTo;
    }

}
