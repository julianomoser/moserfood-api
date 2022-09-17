package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Component
public class GrupoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDTO toDTO(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> toCollectionDTO(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
