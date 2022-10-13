package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.assembler.GrupoDTOAssembler;
import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.service.UsuarioService;
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
@RequestMapping(path = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private MoserLinks moserLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findOrFail(usuarioId);

        CollectionModel<GrupoDTO> gruposDTO = grupoDTOAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks()
                .add(moserLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

        gruposDTO.getContent().forEach(grupoDTO -> {
            grupoDTO.add(moserLinks.linkToUsuarioGrupoDesassociacao(
                    usuarioId, grupoDTO.getId(), "desassociar"));
        });
        return gruposDTO;
    }


    @PutMapping(path = "/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}
