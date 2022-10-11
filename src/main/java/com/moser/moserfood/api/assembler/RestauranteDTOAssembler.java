package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.RestauranteController;
import com.moser.moserfood.api.controller.UsuarioController;
import com.moser.moserfood.api.model.RestauranteDTO;
import com.moser.moserfood.api.model.UsuarioDTO;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Juliano Moser
 */
@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public RestauranteDTOAssembler() {
        super(RestauranteController.class, RestauranteDTO.class);
    }

    @Override
    public RestauranteDTO toModel(Restaurante restaurante) {
        RestauranteDTO restauranteDTO = createModelWithId(restaurante.getId(), restaurante);
        restauranteDTO.add(moserLinks.linkToRestaurantes("restaurantes"));
        restauranteDTO.getCozinha().add(moserLinks.linkToCozinha(restaurante.getCozinha().getId()));
        restauranteDTO.getEndereco().getCidade().add(
                moserLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        restauranteDTO.add(moserLinks.linkToFormaPagamento(restauranteDTO.getId(),
                "formas-pagamento"));
        restauranteDTO.add(moserLinks.linkToResponsaveisRestaurante(restauranteDTO.getId(),
                "responsáveis"));
        return restauranteDTO;
    }

    @Override
    public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(moserLinks.linkToRestaurantes());
    }
}
