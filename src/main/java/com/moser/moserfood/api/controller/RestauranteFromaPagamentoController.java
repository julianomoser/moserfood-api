package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.FormaPagamentoDTOAssembler;
import com.moser.moserfood.api.model.FormaPagamentoDTO;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Restaurant payment method")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFromaPagamentoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @ApiOperation("Lista forma de pagamento por restaurante")
    @GetMapping()
    public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);

        return formaPagamentoDTOAssembler.toCollectionDTO(restaurante.getFormasPagamento());
    }

    @ApiOperation("Associar forma de pagamento")
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
    }

    @ApiOperation("Desassociar forma de pagamento")
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
    }
}
