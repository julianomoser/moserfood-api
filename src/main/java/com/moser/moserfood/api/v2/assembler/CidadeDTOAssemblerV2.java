package com.moser.moserfood.api.v2.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.CidadeController;
import com.moser.moserfood.api.v1.model.CidadeDTO;
import com.moser.moserfood.api.v2.MoserLinksV2;
import com.moser.moserfood.api.v2.controller.CidadeControllerV2;
import com.moser.moserfood.api.v2.model.CidadeDTOV2;
import com.moser.moserfood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class CidadeDTOAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeDTOV2> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinksV2 moserLinks;

    public CidadeDTOAssemblerV2() {
        super(CidadeControllerV2.class, CidadeDTOV2.class);
    }

    @Override
    public CidadeDTOV2 toModel(Cidade cidade) {

        CidadeDTOV2 cidadeDTO = createModelWithId(cidade.getId(), cidade);
        modelMapper.map(cidade, cidadeDTO);
        cidadeDTO.add(moserLinks.linkToCidades("cidades"));
        return cidadeDTO;
    }

    @Override
    public CollectionModel<CidadeDTOV2> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(moserLinks.linkToCidades());
    }
}
