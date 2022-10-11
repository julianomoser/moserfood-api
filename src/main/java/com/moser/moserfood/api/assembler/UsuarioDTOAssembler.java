package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.controller.UsuarioController;
import com.moser.moserfood.api.controller.UsuarioGrupoController;
import com.moser.moserfood.api.model.UsuarioDTO;
import com.moser.moserfood.domain.model.Usuario;
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
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;

    public UsuarioDTOAssembler() {
        super(UsuarioController.class, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO toModel(Usuario usuario) {
        UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioDTO);

        usuarioDTO.add(moserLinks.linkToUsuarios("usuarios"));
        usuarioDTO.add(moserLinks.linkToGruposUsuario(usuario.getId(),"grupos-usuario"));

        return usuarioDTO;
    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
    }
}
