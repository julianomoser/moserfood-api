package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.PedidoController;
import com.moser.moserfood.api.v1.model.PedidoDTO;
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
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    public PedidoDTOAssembler() {
        super(PedidoController.class, PedidoDTO.class);
    }

    public PedidoDTO toModel(Pedido pedido) {

        PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoDTO);

        pedidoDTO.add(moserLinks.linkToPedidos("pedidos"));

        if (moserSecurity.podeGerenciarPedidos(pedido.getCodigo())) {
            if (pedido.podeSerConfimado()) {
                pedidoDTO.add(moserLinks.linkToConfimacaoPedido(pedido.getCodigo(), "confirmar"));
            }

            if (pedido.podeSerCancelado()) {
                pedidoDTO.add(moserLinks.linkToCancelarPedido(pedido.getCodigo(), "cancelar"));
            }

            if (pedido.podeSerEntregue()) {
                pedidoDTO.add(moserLinks.linkToEntregarPedido(pedido.getCodigo(), "entregar"));
            }
        }

        pedidoDTO.getRestaurante().add(
                moserLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoDTO.getCliente().add(
                moserLinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoDTO.getFormaPagamento().add(
                moserLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoDTO.getEnderecoEntrega().getCidade().add(
                moserLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));


        pedidoDTO.getItens().forEach(item -> {
            item.add(moserLinks.linkToProduto(
                    pedidoDTO.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });
        return pedidoDTO;
    }

    @Override
    public CollectionModel<PedidoDTO> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities).add(moserLinks.linkToPedidos("pedidos"));
    }


}
