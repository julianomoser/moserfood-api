package com.moser.moserfood.api.v1.openapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class LinksModelOpenApi {

    private LinkModel rel;

    @Getter
    @Setter
    private static class LinkModel {
        private String href;
        private boolean templated;
    }

}
