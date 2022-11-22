package com.moser.moserfood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Juliano Moser
 */
public @interface CheckSecurity {

    @interface Cozinhas {
        @PreAuthorize("hasAnyAuthority('EDITAR_COZINHAS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeEditar { }

        @PreAuthorize("isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {
        }
    }
}
