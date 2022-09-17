package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe cadastro de estado com código %d", estadoId));
    }
}
