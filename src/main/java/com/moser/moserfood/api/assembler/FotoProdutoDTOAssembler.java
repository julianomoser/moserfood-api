package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.RestauranteProdutoFotoController;
import com.moser.moserfood.api.model.FotoProdutoDTO;
import com.moser.moserfood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class FotoProdutoDTOAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public FotoProdutoDTOAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoDTO.class);
    }

    @Override
    public FotoProdutoDTO toModel(FotoProduto foto) {
        FotoProdutoDTO fotoProdutoDTO = modelMapper.map(foto, FotoProdutoDTO.class);
        fotoProdutoDTO.add(
                moserLinks.linkToProduto(foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
        return fotoProdutoDTO;
    }
}
