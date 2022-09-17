package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;

    public GrupoNaoEncontradoException(String message) {
        super(message);
    }

    public GrupoNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de grupo com código %d", estadoId));
    }
}
