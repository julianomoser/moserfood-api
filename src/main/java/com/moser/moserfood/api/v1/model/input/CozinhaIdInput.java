package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Setter
@Getter
public class CozinhaIdInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long id;
}
