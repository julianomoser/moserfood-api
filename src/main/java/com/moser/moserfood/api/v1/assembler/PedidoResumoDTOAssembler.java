package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.PedidoController;
import com.moser.moserfood.api.v1.model.PedidoResumoDTO;
import com.moser.moserfood.core.security.MoserSecurity;
import com.moser.moserfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    public PedidoResumoDTOAssembler() {
        super(PedidoController.class, PedidoResumoDTO.class);
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido) {
        PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoResumoDTO);

        if (moserSecurity.podePesquisarPedidos()) {
            pedidoResumoDTO.add(moserLinks.linkToPedidos("pedidos"));
        }

        if (moserSecurity.podeConsultarRestaurantes()) {
            pedidoResumoDTO.getRestaurante().add(moserLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (moserSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoResumoDTO.getCliente().add(moserLinks.linkToUsuario(pedido.getCliente().getId()));
        }

        return pedidoResumoDTO;
    }

    @Override
    public CollectionModel<PedidoResumoDTO> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities).add(moserLinks.linkToPedidos("pedidos"));
    }
}
