package com.moser.moserfood.domain.event;

import com.moser.moserfood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Juliano Moser
 */
@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;
}
