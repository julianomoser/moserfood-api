package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.assembler.UsuarioDTOAssembler;
import com.moser.moserfood.api.v1.model.UsuarioDTO;
import com.moser.moserfood.core.security.CheckSecurity;
import com.moser.moserfood.core.security.MoserSecurity;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping
    public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);
        final CollectionModel<UsuarioDTO> usuariosDTO = usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks()
                .add(moserLinks.linkToResponsaveisRestaurante(restauranteId));

        if (moserSecurity.podeGerenciarCadastroRestaurantes()) {
            usuariosDTO.add(moserLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

            usuariosDTO.getContent().forEach(usuarioDTO -> {
                usuarioDTO.add(moserLinks.linkToRestauranteResponsavelDesassociacao(
                        restauranteId, usuarioDTO.getId(), "desassociar"));
            });
        }

        return usuariosDTO;
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
