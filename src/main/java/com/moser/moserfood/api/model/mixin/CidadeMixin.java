package com.moser.moserfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moser.moserfood.domain.model.Estado;

/**
 * @author Juliano Moser
 */
public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
