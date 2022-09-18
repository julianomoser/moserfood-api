package com.moser.moserfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Setter
@Getter
public class CozinhaIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;
}
