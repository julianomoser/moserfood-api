package com.moser.moserfood.api.assembler;

import com.moser.moserfood.api.model.input.RestauranteInput;
import com.moser.moserfood.domain.model.Cidade;
import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDTOObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDTOObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.moser.moserfood.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());
        if (restaurante.getEndereco() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput, restaurante);
    }
}
