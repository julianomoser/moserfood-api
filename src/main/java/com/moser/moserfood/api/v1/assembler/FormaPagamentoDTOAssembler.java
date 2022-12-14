package com.moser.moserfood.api.v1.assembler;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.controller.FormaPagamentoController;
import com.moser.moserfood.api.v1.model.FormaPagamentoDTO;
import com.moser.moserfood.core.security.MoserSecurity;
import com.moser.moserfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class FormaPagamentoDTOAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    public FormaPagamentoDTOAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoDTO.class);
    }

    @Override
    public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
        FormaPagamentoDTO formaPagamentoDTO = createModelWithId(formaPagamento.getId(), formaPagamento);
        modelMapper.map(formaPagamento, formaPagamentoDTO);

        if (moserSecurity.podeConsultarFormasPagamento()) {
            formaPagamentoDTO.add(moserLinks.linkToFormasPagamento("formasPagamento"));
        }

        return formaPagamentoDTO;
    }

    @Override
    public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        CollectionModel<FormaPagamentoDTO> collectionModel = super.toCollectionModel(entities);

        if (moserSecurity.podeConsultarFormasPagamento()) {
            collectionModel.add(moserLinks.linkToFormasPagamento());
        }

        return collectionModel;
    }
}
