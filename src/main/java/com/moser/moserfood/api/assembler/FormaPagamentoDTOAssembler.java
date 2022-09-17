package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.FormaPagamentoDTO;
import com.moser.moserfood.domain.model.FormaPagamento;
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
public class FormaPagamentoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    public List<FormaPagamentoDTO> toCollectionDTO(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
