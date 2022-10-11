package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.CozinhaController;
import com.moser.moserfood.api.model.CozinhaDTO;
import com.moser.moserfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Juliano Moser
 */
@Component
public class CozinhaDTOAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public CozinhaDTOAssembler() {
        super(CozinhaController.class, CozinhaDTO.class);
    }

    public CozinhaDTO toModel(Cozinha cozinha) {
        CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDTO);
        cozinhaDTO.add(moserLinks.linkToCozinhas("cozinhas"));
        return cozinhaDTO;
    }

    @Override
    public CollectionModel<CozinhaDTO> toCollectionModel(Iterable<? extends Cozinha> entities) {
        return super.toCollectionModel(entities).add(linkTo(CozinhaController.class).withSelfRel());
    }
}
