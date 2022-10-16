package com.moser.moserfood.api.v1.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moser.moserfood.domain.model.Estado;

/**
 * @author Juliano Moser
 */
public class CozinhaMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}
