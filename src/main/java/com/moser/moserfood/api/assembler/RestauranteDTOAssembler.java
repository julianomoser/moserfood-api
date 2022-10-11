package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.RestauranteController;
import com.moser.moserfood.api.model.RestauranteDTO;
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

        if (restaurante.ativacaoPermitida()) {
            restauranteDTO.add(
                    moserLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteDTO.add(
                    moserLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteDTO.add(
                    moserLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteDTO.add(
                    moserLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        restauranteDTO.add(moserLinks.linkToProdutos(restaurante.getId(), "produtos"));

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
