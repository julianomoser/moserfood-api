package com.moser.moserfood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Juliano Moser
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problema")
public class Problem {

    @Schema(example = "400")
    private Integer status;
    @Schema(example = "2022-09-18T22:57:11.513187Z")
    private OffsetDateTime timestamp;
    @Schema(example = "https://moserfood.com.br/dados-invalidos")
    private String type;
    @Schema(example = "Dados inválidos")
    private String title;
    @Schema(example = "um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String detail;
    @Schema(example = "um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String userMessage;
    @Schema(description = "Lista de objetos com campos que geraram o erro")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(name = "ObjetoProblema")
    public static class Object {
        @Schema(example = "preço")
        private String name;
        @Schema(example = "O preço é obrigatório")
        private String userMessage;
    }
}
