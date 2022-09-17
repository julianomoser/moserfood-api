package com.moser.moserfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class UsuarioInput {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
}
