package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.ResourceUriHelper;
import com.moser.moserfood.api.assembler.CidadeDTOAssembler;
import com.moser.moserfood.api.assembler.CidadeInputDisassembler;
import com.moser.moserfood.api.openapi.controller.CidadeControllerOpenApi;
import com.moser.moserfood.api.model.CidadeDTO;
import com.moser.moserfood.api.model.input.CidadeInput;
import com.moser.moserfood.domain.exception.EstadoNaoEncontradoException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.model.Cidade;
import com.moser.moserfood.domain.repository.CidadeRepository;
import com.moser.moserfood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Juliano Moser
 */

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeDTOAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CidadeDTO> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionDTO(todasCidades);
    }

    @GetMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.findOrFail(cidadeId);
        CidadeDTO cidadeDTO = cidadeModelAssembler.toDTO(cidade);
        cidadeDTO.add(Link.of("http://api.moserfood.local:8081/cidades/1"));
        cidadeDTO.add(Link.of("http://api.moserfood.local:8081/cidades", "cidades"));
        cidadeDTO.getEstado().add(Link.of("http://api.moserfood.local:8081/estados/1"));
        return cidadeDTO;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO salvar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cidadeService.salvar(cidade);

            CidadeDTO cidadeDTO = cidadeModelAssembler.toDTO(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeDTO.getId());

            return cidadeDTO;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CidadeDTO atualizar(@PathVariable Long cidadeId,
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

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }
}
