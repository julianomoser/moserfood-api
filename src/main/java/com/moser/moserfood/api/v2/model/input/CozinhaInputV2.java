package com.moser.moserfood.api.v2.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Setter
@Getter
public class CozinhaInputV2 {

    @NotBlank
    private String nomeCozinha;
}
