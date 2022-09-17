package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.EstadoDTOAssembler;
import com.moser.moserfood.api.assembler.EstadoInputDisassembler;
import com.moser.moserfood.api.model.EstadoDTO;
import com.moser.moserfood.api.model.input.EstadoInput;
import com.moser.moserfood.domain.model.Estado;
import com.moser.moserfood.domain.repository.EstadoRepository;
import com.moser.moserfood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
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

    @GetMapping
    public List<EstadoDTO> lista() {
        List<Estado> todosEstados = estadoRepository.findAll();
        return estadoModelAssembler.toCollectionDTO(todosEstados);
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.findOrFail(estadoId);
        return estadoModelAssembler.toDTO(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO salvar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

        estado = estadoService.salvar(estado);

        return estadoModelAssembler.toDTO(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId,
                               @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.findOrFail(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        estadoAtual = estadoService.salvar(estadoAtual);

        return estadoModelAssembler.toDTO(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }
}
