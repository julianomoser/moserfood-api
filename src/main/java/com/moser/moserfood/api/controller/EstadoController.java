package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.EstadoDTOAssembler;
import com.moser.moserfood.api.assembler.EstadoInputDisassembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.EstadoDTO;
import com.moser.moserfood.api.model.input.EstadoInput;
import com.moser.moserfood.domain.model.Estado;
import com.moser.moserfood.domain.repository.EstadoRepository;
import com.moser.moserfood.domain.service.EstadoService;
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
@Api(tags = "State")
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoDTOAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @ApiOperation("Lista os estados")
    @GetMapping
    public List<EstadoDTO> lista() {
        List<Estado> todosEstados = estadoRepository.findAll();
        return estadoModelAssembler.toCollectionDTO(todosEstados);
    }

    @ApiOperation("Busca um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.findOrFail(estadoId);
        return estadoModelAssembler.toDTO(estado);
    }

    @ApiOperation("Cadastra um estado")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Estado cadastrada"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO salvar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

        estado = estadoService.salvar(estado);

        return estadoModelAssembler.toDTO(estado);
    }

    @ApiOperation("Atualiza um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId,
                               @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.findOrFail(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        estadoAtual = estadoService.salvar(estadoAtual);

        return estadoModelAssembler.toDTO(estadoAtual);
    }

    @ApiOperation("Exclui um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Foto excluída"),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }
}
