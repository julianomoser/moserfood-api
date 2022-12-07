package com.moser.moserfood.api.v2.openapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class PageDTOV2OpenApi {

    private Long size;

    private Long totalElements;

    private Long totalPages;

    private Long number;
}
