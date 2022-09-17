package com.moser.moserfood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.moser.moserfood.api.model.mixin.CidadeMixin;
import com.moser.moserfood.api.model.mixin.CozinhaMixin;
import com.moser.moserfood.api.model.mixin.UsuarioMixin;
import com.moser.moserfood.domain.model.Cidade;
import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Component
public class JacksonMixinModel extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModel() {
//        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Usuario.class, UsuarioMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
