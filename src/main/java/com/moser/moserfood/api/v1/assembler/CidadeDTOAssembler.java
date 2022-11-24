package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.CidadeController;
import com.moser.moserfood.api.v1.model.CidadeDTO;
import com.moser.moserfood.core.security.MoserSecurity;
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
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    public CidadeDTOAssembler() {
        super(CidadeController.class, CidadeDTO.class);
    }

    @Override
    public CidadeDTO toModel(Cidade cidade) {

        CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);
        modelMapper.map(cidade, cidadeDTO);

        if (moserSecurity.podeConsultarCidades()) {
            cidadeDTO.add(moserLinks.linkToCidades("cidades"));
        }

        if (moserSecurity.podeConsultarEstados()) {
            cidadeDTO.getEstado().add(moserLinks.linkToEstado(cidadeDTO.getEstado().getId()));
        }

        return cidadeDTO;
    }

    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
        CollectionModel<CidadeDTO> collectionModel = super.toCollectionModel(entities);

        if (moserSecurity.podeConsultarCidades()) {
            collectionModel.add(moserLinks.linkToCidades());
        }

        return collectionModel;
    }
}
