package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.GrupoDTOAssembler;
import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findOrFail(usuarioId);
        return grupoDTOAssembler.toCollectionModel(usuario.getGrupos());
    }


    @PutMapping(path = "/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
