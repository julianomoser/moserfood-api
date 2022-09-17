package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }

    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format("Não existe cadastro de cidade com código %d", cidadeId));
    }
}
