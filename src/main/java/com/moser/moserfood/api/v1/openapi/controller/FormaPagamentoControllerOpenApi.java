package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.FormaPagamentoDTO;
import com.moser.moserfood.api.v1.model.input.FormaPagamentoInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Juliano Moser
 */
public interface FormaPagamentoControllerOpenApi {


    ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<FormaPagamentoDTO> buscar(Long formaPagamentoId,
                                             ServletWebRequest request);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada"))
    FormaPagamentoDTO salvar(FormaPagamentoInput formaPagamentoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "FormaPagamentoDTO atualizado"),
            @ApiResponse(responseCode = "404", description = "FormaPagamentoDTO não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    FormaPagamentoDTO atualizar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "FormaPagamentoDTO excluído"),
            @ApiResponse(responseCode = "404", description = "FormaPagamentoDTO não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long formaPagamentoId);

}
