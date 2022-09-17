package com.moser.moserfood.api.model.input;

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
