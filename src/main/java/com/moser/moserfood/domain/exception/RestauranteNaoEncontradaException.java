package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;

    public RestauranteNaoEncontradaException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradaException(Long restauranteId) {
        this(String.format("Não existe cadastro de restaurante com código %d", restauranteId));
    }
}
