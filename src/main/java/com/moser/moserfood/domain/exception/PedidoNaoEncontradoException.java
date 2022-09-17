package com.moser.moserfood.domain.exception;

/**
 * @author Juliano Moser
 */
public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1;



    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Não existe um pedido com código %s", codigoPedido));
    }
}
