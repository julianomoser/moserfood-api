package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.CozinhaDTO;
import com.moser.moserfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDTO toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toCollectionDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
