package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.GrupoDTOAssembler;
import com.moser.moserfood.api.assembler.GrupoInputDisassembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.api.model.input.GrupoInput;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.repository.GrupoRepository;
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

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Group")
@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoDTOAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @ApiOperation("Lista os grupos")
    @GetMapping
    public List<GrupoDTO> listar() {
        List<Grupo> todasGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionDTO(todasGrupos);
    }

    @ApiOperation("Busca um gurpo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.findOrFail(grupoId);

        return grupoModelAssembler.toDTO(grupo);
    }

    @ApiOperation("Cadastra um grupo")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Grupo cadastrado"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO salvar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        grupo = grupoService.salvar(grupo);

        return grupoModelAssembler.toDTO(grupo);
    }

    @ApiOperation("Atualiza um grupo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo atualizado"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId,
                              @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = grupoService.findOrFail(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = grupoService.salvar(grupoAtual);

        return grupoModelAssembler.toDTO(grupoAtual);
    }

    @ApiOperation("Exclui um grupo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Grupo excluído"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }
}
