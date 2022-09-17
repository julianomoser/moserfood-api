package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.FotoProdutoDTO;
import com.moser.moserfood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class FotoProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDTO toDTO(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoDTO.class);
    }
}
