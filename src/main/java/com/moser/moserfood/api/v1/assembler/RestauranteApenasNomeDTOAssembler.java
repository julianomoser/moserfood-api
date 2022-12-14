package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.RestauranteController;
import com.moser.moserfood.api.v1.model.RestauranteApenasNomeDTO;
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
public class RestauranteApenasNomeDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;


    public RestauranteApenasNomeDTOAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeDTO.class);
    }

    @Override
    public RestauranteApenasNomeDTO toModel(Restaurante restaurante) {
        RestauranteApenasNomeDTO restauranteApenasNomeDTO = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteApenasNomeDTO);

        if (moserSecurity.podeConsultarRestaurantes()) {
            restauranteApenasNomeDTO.add(moserLinks.linkToRestaurantes("restaurantes"));
        }

        return restauranteApenasNomeDTO;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteApenasNomeDTO> collectionModel = super.toCollectionModel(entities);

        if (moserSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(moserLinks.linkToRestaurantes());
        }

        return collectionModel;
    }
}
