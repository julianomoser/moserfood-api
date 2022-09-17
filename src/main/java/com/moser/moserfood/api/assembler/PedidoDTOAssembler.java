package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.PedidoDTO;
import com.moser.moserfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Component
public class PedidoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public List<PedidoDTO> toCollectionDTO(Collection<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
