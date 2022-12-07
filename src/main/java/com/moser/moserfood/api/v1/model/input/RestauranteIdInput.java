package com.moser.moserfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class RestauranteIdInput {


    @NotNull
    private Long id;
}
