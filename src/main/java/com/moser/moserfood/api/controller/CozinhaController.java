package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.CozinhaDTOAssembler;
import com.moser.moserfood.api.assembler.CozinhaInputDisassembler;
import com.moser.moserfood.api.model.CozinhaDTO;
import com.moser.moserfood.api.model.input.CozinhaInput;
import com.moser.moserfood.api.openapi.controller.CozinhaControllerOpenApi;
import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.repository.CozinhaRepository;
import com.moser.moserfood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Juliano Moser
 */

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaDTOAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        List<CozinhaDTO> cozinhasDTO = cozinhaModelAssembler.toCollectionDTO(cozinhasPage.getContent());

        return new PageImpl<>(cozinhasDTO, pageable, cozinhasPage.getTotalElements());
    }

    @GetMapping(path = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaService.findOrFail(cozinhaId);

        return cozinhaModelAssembler.toDTO(cozinha);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO savlar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cozinhaService.salvar(cozinha);

        return cozinhaModelAssembler.toDTO(cozinha);
    }

    @PutMapping(path = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId,
                                @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cozinhaService.findOrFail(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

        return cozinhaModelAssembler.toDTO(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }

    @GetMapping(path = "/first", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Cozinha> firstRestaurante() {
        return cozinhaRepository.findFirst();
    }

}
