package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.assembler.PermissaoDTOAssembler;
import com.moser.moserfood.api.v1.model.PermissaoDTO;
import com.moser.moserfood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.moser.moserfood.core.security.CheckSecurity;
import com.moser.moserfood.core.security.MoserSecurity;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.service.GrupoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Juliano Moser
 */
@Api(tags = "Group permission")
@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoDTOAssembler permissaoModelAssembler;

    @Autowired
    private MoserLinks moserLinks;

    @Autowired
    private MoserSecurity moserSecurity;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.findOrFail(grupoId);

        CollectionModel<PermissaoDTO> permissoesDTOS
                = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
                .removeLinks();

        permissoesDTOS.add(moserLinks.linkToGrupoPermissoes(grupoId));

        if (moserSecurity.podeEditarUsuariosGruposPermissoes()) {
            permissoesDTOS.add(moserLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

            permissoesDTOS.getContent().forEach(permissaoDTO -> {
                permissaoDTO.add(moserLinks.linkToGrupoPermissaoDesassociacao(
                        grupoId, permissaoDTO.getId(), "desassociar"));
            });
        }

        return permissoesDTOS;
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
}
