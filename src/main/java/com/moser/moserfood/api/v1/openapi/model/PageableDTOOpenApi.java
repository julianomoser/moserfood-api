package com.moser.moserfood.api.v1.openapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class PageableDTOOpenApi {

    private int page;
    private int size;
    List<String> sort;
}
