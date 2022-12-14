package com.moser.moserfood.core.security.authorizationserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("moserfood.auth")
public class MoserFoodSecurityProperties {

    @NotBlank
    private String providerUrl;
}

