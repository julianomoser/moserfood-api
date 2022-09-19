package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.CidadeDTOAssembler;
import com.moser.moserfood.api.assembler.CidadeInputDisassembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.CidadeDTO;
import com.moser.moserfood.api.model.input.CidadeInput;
import com.moser.moserfood.domain.exception.EstadoNaoEncontradoException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.model.Cidade;
import com.moser.moserfood.domain.repository.CidadeRepository;
import com.moser.moserfood.domain.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "City")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeDTOAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @ApiOperation("Lista as cidades")
    @GetMapping
    public List<CidadeDTO> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionDTO(todasCidades);
    }

    @ApiOperation("Busca uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.findOrFail(cidadeId);

        return cidadeModelAssembler.toDTO(cidade);
    }

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                            @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cidadeService.salvar(cidade);

            return cidadeModelAssembler.toDTO(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Atualiza uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId,
                               @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
                               @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cidadeService.findOrFail(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cidadeService.salvar(cidadeAtual);

            return cidadeModelAssembler.toDTO(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Exclui uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }
}
