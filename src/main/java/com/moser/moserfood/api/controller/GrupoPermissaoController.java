package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.PermissaoDTOAssembler;
import com.moser.moserfood.api.model.PermissaoDTO;
import com.moser.moserfood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.service.GrupoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Group permission")
@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoDTOAssembler permissaoModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.findOrFail(grupoId);

        return permissaoModelAssembler.toCollectionDTO(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }
}
