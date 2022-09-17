package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.ProdutoDTO;
import com.moser.moserfood.domain.model.Produto;
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
public class ProdutoDTOAssembler {

    @Autowired

    private ModelMapper modelMapper;

    public ProdutoDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toCollectionDTO(Collection<Produto> produtos) {
        return produtos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
