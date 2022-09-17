package com.moser.moserfood.core.modelmapper;

import com.moser.moserfood.api.model.EnderecoDTO;
import com.moser.moserfood.api.model.input.ItemPedidoInput;
import com.moser.moserfood.domain.model.Endereco;
import com.moser.moserfood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Juliano Moser
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoDTOTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoDTO.class);

        enderecoToEnderecoDTOTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoDTODest, value) -> enderecoDTODest.getCidade().setEstado(value));

        return modelMapper;
    }

}
