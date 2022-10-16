package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.RestauranteProdutoController;
import com.moser.moserfood.api.v1.model.ProdutoDTO;
import com.moser.moserfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public ProdutoDTOAssembler() {
        super(RestauranteProdutoController.class, ProdutoDTO.class);
    }

    public ProdutoDTO toModel(Produto produto) {
        ProdutoDTO produtoDTO = createModelWithId(produto.getId(), produto);
        modelMapper.map(produto, produtoDTO);
        produtoDTO.add(moserLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
        produtoDTO.add(moserLinks.linkToFotoProduto(
                produto.getRestaurante().getId(), produto.getId(),"produtos"));
        return produtoDTO;
    }
}
