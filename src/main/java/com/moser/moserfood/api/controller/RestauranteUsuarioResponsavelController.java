package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.assembler.UsuarioDTOAssembler;
import com.moser.moserfood.api.model.UsuarioDTO;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Juliano Moser
 */
@Api(tags = "Restaurant user")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;
    @Autowired
    private MoserLinks moserLinks;

    @ApiOperation("Lista os responsáveis")
    @GetMapping
    public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);
        final CollectionModel<UsuarioDTO> usuariosDTO = usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks()
                .add(moserLinks.linkToResponsaveisRestaurante(restauranteId))
                .add(moserLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

        usuariosDTO.getContent().forEach(usuarioDTO -> {
            usuarioDTO.add(moserLinks.linkToRestauranteResponsavelDesassociacao(
                    restauranteId, usuarioDTO.getId(), "desassociar"));
        });

        return usuariosDTO;
    }

    @ApiOperation("Associar responsável a um restaurante")
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Desassociar responsável de um restaurante")
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
