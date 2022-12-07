package com.moser.moserfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Setter
@Getter
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
