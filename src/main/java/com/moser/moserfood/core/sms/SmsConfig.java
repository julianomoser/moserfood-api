package com.moser.moserfood.core.sms;

import com.moser.moserfood.domain.service.EnvioSmsService;
import com.moser.moserfood.infrastructure.service.sms.FakeSmsEnvioService;
import com.moser.moserfood.infrastructure.service.sms.TwilioSmsEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Juliano Moser
 */
@Configuration
public class SmsConfig {

    @Autowired
    private SmsProperties smsProperties;

    @Bean
    public EnvioSmsService envioSmsService() {
        switch (smsProperties.getImpl()) {
            case FAKE:
                return new FakeSmsEnvioService();
            case TWILIO:
                return new TwilioSmsEnvioService();
            default:
                return null;
        }
    }
}
