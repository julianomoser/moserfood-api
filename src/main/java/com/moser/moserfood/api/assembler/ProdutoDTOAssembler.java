package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.RestauranteProdutoController;
import com.moser.moserfood.api.model.CidadeDTO;
import com.moser.moserfood.api.model.ProdutoDTO;
import com.moser.moserfood.domain.model.Cidade;
import com.moser.moserfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
