package com.moser.moserfood.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.MediaType;

/**
 * @author Juliano Moser
 */
@Configuration
public class MoserfoodConfiguration {

    @Bean
    public HalConfiguration globalPolicy() {
        return new HalConfiguration()
                .withMediaType(MediaType.APPLICATION_JSON)
                .withMediaType(MoserMediaTypes.V1_APPLICATION_JSON)
                .withMediaType(MoserMediaTypes.V2_APPLICATION_JSON);
    }
}
