package com.moser.moserfood.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
}
