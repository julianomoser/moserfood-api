package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class UsuarioInput {

    @Schema(example = "Plini", required = true)
    @NotBlank
    private String nome;
    @Schema(example = "plini@moserfood.com", required = true)
    @NotBlank
    @Email
    private String email;
}
