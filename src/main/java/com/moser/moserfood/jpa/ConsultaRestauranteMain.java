package com.moser.moserfood.jpa;

import com.moser.moserfood.MoserfoodApiApplication;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author Juliano Moser
 */
public class ConsultaRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MoserfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        List<Restaurante> restaurantes = restauranteRepository.findAll();

        restaurantes.forEach(restaurante ->
                System.out.printf("%s - %.2f - %s\n", restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome()));
    }
}
