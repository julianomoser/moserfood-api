package com.moser.moserfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;

/**
 * @author Juliano Moser
 */
public class UsuarioMixin {

    @JsonIgnore
    private OffsetDateTime dataCadastro;

}
