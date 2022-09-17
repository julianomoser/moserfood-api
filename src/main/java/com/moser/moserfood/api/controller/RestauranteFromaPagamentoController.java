package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.FormaPagamentoDTOAssembler;
import com.moser.moserfood.api.model.FormaPagamentoDTO;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFromaPagamentoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @GetMapping()
    public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);

        return formaPagamentoDTOAssembler.toCollectionDTO(restaurante.getFormasPagamento());
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
    }
}
