package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.RestauranteController;
import com.moser.moserfood.api.v1.model.RestauranteDTO;
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
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    @Autowired
    private MoserSecurity moserSecurity;

    public RestauranteDTOAssembler() {
        super(RestauranteController.class, RestauranteDTO.class);
    }

    @Override
    public RestauranteDTO toModel(Restaurante restaurante) {
        RestauranteDTO restauranteDTO = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteDTO);

        if (moserSecurity.podeConsultarRestaurantes()) {
            restauranteDTO.add(moserLinks.linkToRestaurantes("restaurantes"));
        }

        if (moserSecurity.podeGerenciarCadastroRestaurantes()) {
            if (restaurante.ativacaoPermitida()) {
                restauranteDTO.add(
                        moserLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
            }

            if (restaurante.inativacaoPermitida()) {
                restauranteDTO.add(
                        moserLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
            }
        }

        if (moserSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
            if (restaurante.aberturaPermitida()) {
                restauranteDTO.add(
                        moserLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
            }

            if (restaurante.fechamentoPermitido()) {
                restauranteDTO.add(
                        moserLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
            }
        }

        if (moserSecurity.podeConsultarRestaurantes()) {
            restauranteDTO.add(moserLinks.linkToProdutos(restaurante.getId(), "produtos"));
        }

        if (moserSecurity.podeConsultarCozinhas()) {
            restauranteDTO.getCozinha().add(moserLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }

        if (moserSecurity.podeConsultarCidades()) {
            if (restauranteDTO.getEndereco() != null
                    && restauranteDTO.getEndereco().getCidade() != null) {
                restauranteDTO.getEndereco().getCidade().add(
                        moserLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
            }
        }

        if (moserSecurity.podeConsultarRestaurantes()) {
            restauranteDTO.add(moserLinks.linkToFormaPagamento(restauranteDTO.getId(),
                    "formas-pagamento"));
        }

        if (moserSecurity.podeGerenciarCadastroRestaurantes()) {
            restauranteDTO.add(moserLinks.linkToResponsaveisRestaurante(restauranteDTO.getId(),
                    "responsáveis"));
        }

        return restauranteDTO;
    }

    @Override
    public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteDTO> collectionModel = super.toCollectionModel(entities);

        if (moserSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(moserLinks.linkToRestaurantes());
        }

        return collectionModel;
    }
}
