package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.assembler.EstadoDTOAssembler;
import com.moser.moserfood.api.v1.assembler.EstadoInputDisassembler;
import com.moser.moserfood.api.v1.model.EstadoDTO;
import com.moser.moserfood.api.v1.model.input.EstadoInput;
import com.moser.moserfood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.moser.moserfood.domain.model.Estado;
import com.moser.moserfood.domain.repository.EstadoRepository;
import com.moser.moserfood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoDTOAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EstadoDTO> listar() {
        List<Estado> todosEstados = estadoRepository.findAll();
        return estadoModelAssembler.toCollectionModel(todosEstados);
    }

    @GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.findOrFail(estadoId);
        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO salvar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

        estado = estadoService.salvar(estado);

        return estadoModelAssembler.toModel(estado);
    }

    @PutMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoDTO atualizar(@PathVariable Long estadoId,
                               @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.findOrFail(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        estadoAtual = estadoService.salvar(estadoAtual);

        return estadoModelAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }
}
