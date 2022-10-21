package com.moser.moserfood.api.v2;

import com.moser.moserfood.api.v1.controller.*;
import com.moser.moserfood.api.v2.controller.CidadeControllerV2;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.TemplateVariable.VariableType;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Juliano Moser
 */
@Component
public class MoserLinksV2 {

    public Link linkToCidades(String rel) {
        return linkTo(CidadeControllerV2.class).withRel(rel);
    }

    public Link linkToCidades() {
        return linkToCidades(IanaLinkRelations.SELF.value());
    }
}
