package com.moser.moserfood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("moserfood.email")
public class EmailProperties {

    @NotNull
    private String remetente;
    private Implementacao impl = Implementacao.FAKE;
    private SandBox sandbox = new SandBox();

    public enum Implementacao {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class SandBox {
        private String destinatario;
    }
}
