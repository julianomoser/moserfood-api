package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.EstadoController;
import com.moser.moserfood.api.controller.UsuarioController;
import com.moser.moserfood.api.model.EstadoDTO;
import com.moser.moserfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Juliano Moser
 */
@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public EstadoDTOAssembler() {
        super(EstadoController.class, EstadoDTO.class);
    }

    @Override
    public EstadoDTO toModel(Estado estado) {
        EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoDTO);
        estadoDTO.add(moserLinks.linkToEstados("estados"));
        return estadoDTO;
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities).add(linkTo(EstadoController.class).withSelfRel());
    }
}
