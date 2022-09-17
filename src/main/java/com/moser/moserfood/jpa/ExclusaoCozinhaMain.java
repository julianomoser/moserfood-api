package com.moser.moserfood.jpa;

import com.moser.moserfood.MoserfoodApiApplication;
import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @author Juliano Moser
 */
public class ExclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MoserfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(2L);
        cozinhaRepository.deleteById(cozinha.getId());

    }
}
