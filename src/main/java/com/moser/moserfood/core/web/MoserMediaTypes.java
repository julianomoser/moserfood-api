package com.moser.moserfood.core.web;

import org.springframework.http.MediaType;

/**
 * @author Juliano Moser
 */
public class MoserMediaTypes {

    public static final String V1_APPLICATION_JSON_VALUE = "application/vdn.moserfood.v1+json";
    public static final MediaType V1_APPLICATION_JSON = MediaType.valueOf(V1_APPLICATION_JSON_VALUE);
}
