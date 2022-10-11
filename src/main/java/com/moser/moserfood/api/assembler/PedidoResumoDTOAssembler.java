package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.PedidoController;
import com.moser.moserfood.api.controller.RestauranteController;
import com.moser.moserfood.api.controller.UsuarioController;
import com.moser.moserfood.api.model.PedidoResumoDTO;
import com.moser.moserfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Juliano Moser
 */
@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public PedidoResumoDTOAssembler() {
        super(PedidoController.class, PedidoResumoDTO.class);
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido) {
        PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoResumoDTO);

        pedidoResumoDTO.add(moserLinks.linkToPedidos("pedidos"));
        pedidoResumoDTO.getRestaurante().add(moserLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoResumoDTO.getCliente().add(moserLinks.linkToUsuario(pedido.getCliente().getId()));

        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    @Override
    public CollectionModel<PedidoResumoDTO> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities).add(moserLinks.linkToPedidos("pedidos"));
    }
}
