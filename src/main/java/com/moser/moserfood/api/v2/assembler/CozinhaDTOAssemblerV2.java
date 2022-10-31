package com.moser.moserfood.api.v2.assembler;

import com.moser.moserfood.api.v2.MoserLinksV2;
import com.moser.moserfood.api.v2.controller.CozinhaControllerV2;
import com.moser.moserfood.api.v2.model.CozinhaDTOV2;
import com.moser.moserfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class CozinhaDTOAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTOV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoserLinksV2 moserLinks;

    public CozinhaDTOAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaDTOV2.class);
    }

    @Override
    public CozinhaDTOV2 toModel(Cozinha cozinha) {
        CozinhaDTOV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);

        cozinhaModel.add(moserLinks.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }
}
