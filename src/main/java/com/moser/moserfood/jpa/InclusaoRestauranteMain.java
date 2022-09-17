package com.moser.moserfood.jpa;

import com.moser.moserfood.MoserfoodApiApplication;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
public class InclusaoRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MoserfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante  restaurante = new Restaurante();
        restaurante.setNome("Tuk Tuk Comida Indiana");
        restaurante.setTaxaFrete(new BigDecimal(15));

        restaurante = restauranteRepository.save(restaurante);

        System.out.printf("%d - %s\n", restaurante.getId(), restaurante.getNome());
    }
}
