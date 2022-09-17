package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.PermissaoDTO;
import com.moser.moserfood.domain.model.Permissao;
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
public class PermissaoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDTO toDTO(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    public List<PermissaoDTO> toCollectionDTO(Collection<Permissao> permissaos) {
        return permissaos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
