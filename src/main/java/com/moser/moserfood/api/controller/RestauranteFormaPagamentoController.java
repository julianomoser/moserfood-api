package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.assembler.FormaPagamentoDTOAssembler;
import com.moser.moserfood.api.model.FormaPagamentoDTO;
import com.moser.moserfood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private MoserLinks moserLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);

        CollectionModel<FormaPagamentoDTO> formasPagamentosDTO = formaPagamentoDTOAssembler
                .toCollectionModel(restaurante.getFormasPagamento())
                .removeLinks()
                .add(moserLinks.linkToRestauranteFormasPagamento(restauranteId))
                .add(moserLinks.linkToRestauranteFormasPagamentoAssociacao(restauranteId, "associar"));

        formasPagamentosDTO.getContent().forEach(formaPagamentoDTO -> {
            formaPagamentoDTO.add(moserLinks.linkToRestauranteFormasPagamentoDesassociacao(
                    restauranteId, formaPagamentoDTO.getId(), "desassociar"));
        });

        return formasPagamentosDTO;
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}
