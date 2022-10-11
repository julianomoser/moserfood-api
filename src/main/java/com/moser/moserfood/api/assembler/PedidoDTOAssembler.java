package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.controller.*;
import com.moser.moserfood.api.model.PedidoDTO;
import com.moser.moserfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Juliano Moser
 */
@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTOAssembler() {
        super(PedidoController.class, PedidoDTO.class);
    }

    public PedidoDTO toModel(Pedido pedido) {

        PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoDTO);

        TemplateVariables pageVariables = new TemplateVariables(
          new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
          new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
          new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        pedidoDTO.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables), "pedidos"));

        pedidoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoDTO.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoDTO.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoDTO.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoDTO.getRestaurante().getId(), item.getProdutoId()))
                    .withRel("produto"));
        });
        return pedidoDTO;
    }

    @Override
    public CollectionModel<PedidoDTO> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities).add(linkTo(PedidoController.class).withSelfRel());
    }
}
