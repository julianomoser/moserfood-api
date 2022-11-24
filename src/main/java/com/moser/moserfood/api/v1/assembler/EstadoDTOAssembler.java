package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.EstadoController;
import com.moser.moserfood.api.v1.model.EstadoDTO;
import com.moser.moserfood.core.security.MoserSecurity;
import com.moser.moserfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    public EstadoDTOAssembler() {
        super(EstadoController.class, EstadoDTO.class);
    }

    @Override
    public EstadoDTO toModel(Estado estado) {
        EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoDTO);

        if (moserSecurity.podeConsultarEstados()) {
            estadoDTO.add(moserLinks.linkToEstados("estados"));
        }

        return estadoDTO;
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
        CollectionModel<EstadoDTO> collectionModel = super.toCollectionModel(entities);

        if (moserSecurity.podeConsultarEstados()) {
            collectionModel.add(moserLinks.linkToEstados());
        }

        return collectionModel;
    }
}
