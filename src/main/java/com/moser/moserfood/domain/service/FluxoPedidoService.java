package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.model.Pedido;
import com.moser.moserfood.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class FluxoPedidoService {

    private final EmissaoPedidoService emissaoPedidoService;
    private final PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = findPedido(codigoPedido);
        pedido.confirmar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = findPedido(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = findPedido(codigoPedido);
        pedido.cancelar();

        pedidoRepository.save(pedido);
    }

    private Pedido findPedido(String codigoPedido) {
        return emissaoPedidoService.findOrFail(codigoPedido);
    }
}
