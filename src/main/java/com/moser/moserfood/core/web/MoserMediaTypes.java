package com.moser.moserfood.core.web;

import org.springframework.http.MediaType;

/**
 * @author Juliano Moser
 */
public class MoserMediaTypes {

    public static final String V1_APPLICATION_JSON_VALUE = "application/vnd.moserfood.v1+json";
    public static final MediaType V1_APPLICATION_JSON = MediaType.valueOf(V1_APPLICATION_JSON_VALUE);
    public static final String V2_APPLICATION_JSON_VALUE = "application/vnd.moserfood.v2+json";
    public static final MediaType V2_APPLICATION_JSON = MediaType.valueOf(V2_APPLICATION_JSON_VALUE);
}
