package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;

    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
    }
}
