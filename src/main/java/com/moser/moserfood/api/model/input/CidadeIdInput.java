package com.moser.moserfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CidadeIdInput {

    @NotNull
    private Long id;
}
