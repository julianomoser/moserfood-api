package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.EstadoDTO;
import com.moser.moserfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Component
public class EstadoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
        return estados.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
