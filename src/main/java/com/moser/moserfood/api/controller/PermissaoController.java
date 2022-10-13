package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.PermissaoDTOAssembler;
import com.moser.moserfood.api.model.PermissaoDTO;
import com.moser.moserfood.api.openapi.controller.PermissaoControllerOpenApi;
import com.moser.moserfood.domain.model.Permissao;
import com.moser.moserfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoDTOAssembler permissaoDTOAssembler;

    @Override
    @GetMapping
    public CollectionModel<PermissaoDTO> listar() {
        List<Permissao> todasPermissoes = permissaoRepository.findAll();
        return permissaoDTOAssembler.toCollectionModel(todasPermissoes);
    }
}
