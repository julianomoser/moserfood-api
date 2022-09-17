package com.moser.moserfood.core.email;

import com.moser.moserfood.domain.service.EnvioEmailService;
import com.moser.moserfood.infrastructure.service.email.FakeEnvioEmailService;
import com.moser.moserfood.infrastructure.service.email.SandBoxEnvioEmailService;
import com.moser.moserfood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Juliano Moser
 */
@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandBoxEnvioEmailService();
            default:
                return null;
        }
    }
}
