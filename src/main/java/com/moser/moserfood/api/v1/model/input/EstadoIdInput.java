package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EstadoIdInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long id;
}
