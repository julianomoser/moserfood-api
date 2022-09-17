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
public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MoserfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha  cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");

        cozinha1 = cozinhaRepository.save(cozinha1);
        cozinha2 = cozinhaRepository.save(cozinha2 );

        System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
    }
}
