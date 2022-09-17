package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;

    public FotoProdutoNaoEncontradaException(String message) {
        super(message);
    }

    public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }
}
