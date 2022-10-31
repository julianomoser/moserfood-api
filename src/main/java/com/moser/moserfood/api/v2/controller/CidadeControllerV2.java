package com.moser.moserfood.api.v2.controller;

import com.moser.moserfood.api.ResourceUriHelper;
import com.moser.moserfood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.moser.moserfood.api.v2.assembler.CidadeDTOAssemblerV2;
import com.moser.moserfood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.moser.moserfood.api.v2.model.CidadeDTOV2;
import com.moser.moserfood.api.v2.model.input.CidadeInputV2;
import com.moser.moserfood.api.v2.openapi.controller.CidadeControllerV2OpenApi;
import com.moser.moserfood.domain.exception.EstadoNaoEncontradoException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.model.Cidade;
import com.moser.moserfood.domain.repository.CidadeRepository;
import com.moser.moserfood.domain.service.CidadeService;
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
@RequestMapping(path = "/v2/cidades")
public class CidadeControllerV2 implements CidadeControllerV2OpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeDTOAssemblerV2 cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<CidadeDTOV2> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @GetMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CidadeDTOV2 buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.findOrFail(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTOV2 salvar(@RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cidadeService.salvar(cidade);

            CidadeDTOV2 cidadeDTO = cidadeModelAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeDTO.getIdCidade());

            return cidadeDTO;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CidadeDTOV2 atualizar(@PathVariable Long cidadeId,
                                 @RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidadeAtual = cidadeService.findOrFail(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cidadeService.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }
}
