package com.moser.moserfood.jpa;

import com.moser.moserfood.MoserfoodApiApplication;
import com.moser.moserfood.domain.model.FormaPagamento;
import com.moser.moserfood.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @author Juliano Moser
 */
public class InclusaoFormaPagamentoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MoserfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        FormaPagamentoRepository formaPagamntoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

        FormaPagamento  formaPagamnto1 = new FormaPagamento();
        formaPagamnto1.setDescricao("Débito");

        formaPagamnto1 = formaPagamntoRepository.save(formaPagamnto1);

        System.out.printf("%d - %s\n", formaPagamnto1.getId(), formaPagamnto1.getDescricao());
    }
}
