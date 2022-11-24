package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.RestauranteController;
import com.moser.moserfood.api.v1.model.RestauranteBasicoDTO;
import com.moser.moserfood.core.security.MoserSecurity;
import com.moser.moserfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class RestauranteBasicoDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    public RestauranteBasicoDTOAssembler() {
        super(RestauranteController.class, RestauranteBasicoDTO.class);
    }

    @Override
    public RestauranteBasicoDTO toModel(Restaurante restaurante) {
        RestauranteBasicoDTO restauranteBasicoDTO = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteBasicoDTO);

        if (moserSecurity.podeConsultarRestaurantes()) {
            restauranteBasicoDTO.add(moserLinks.linkToRestaurante(restauranteBasicoDTO.getId()));
        }

        if (moserSecurity.podeConsultarCozinhas()) {
            restauranteBasicoDTO.getCozinha().add(moserLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }

        return restauranteBasicoDTO;
    }

    @Override
    public CollectionModel<RestauranteBasicoDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteBasicoDTO> collectionModel = super.toCollectionModel(entities);

        if (moserSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(moserLinks.linkToRestaurantes());
        }

        return collectionModel;
    }
}
