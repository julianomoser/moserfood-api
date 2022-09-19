package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.PermissaoDTOAssembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.PermissaoDTO;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.service.GrupoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Group permission")
@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoDTOAssembler permissaoModelAssembler;

    @ApiOperation("Lista os grupos de permissões")
    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.findOrFail(grupoId);

        return permissaoModelAssembler.toCollectionDTO(grupo.getPermissoes());
    }

    @ApiOperation("Associonar uma permissão a um gurpo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo associado"),
            @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }

    @ApiOperation("Desassocionar uma permissão a um gurpo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo desassociado"),
            @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desaassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }
}
