package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.GrupoDTOAssembler;
import com.moser.moserfood.api.assembler.GrupoInputDisassembler;
import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.api.model.input.GrupoInput;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.repository.GrupoRepository;
import com.moser.moserfood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
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

    @GetMapping
    public List<GrupoDTO> listar() {
        List<Grupo> todasGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionDTO(todasGrupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.findOrFail(grupoId);

        return grupoModelAssembler.toDTO(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO salvar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        grupo = grupoService.salvar(grupo);

        return grupoModelAssembler.toDTO(grupo);
    }


    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId,
                              @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = grupoService.findOrFail(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = grupoService.salvar(grupoAtual);

        return grupoModelAssembler.toDTO(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }
}
