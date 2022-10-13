package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.model.PermissaoDTO;
import com.moser.moserfood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class PermissaoDTOAssembler implements RepresentationModelAssembler<Permissao, PermissaoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;


    @Override
    public PermissaoDTO toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    @Override
    public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities).add(moserLinks.linkToPermissoes());
    }
}
