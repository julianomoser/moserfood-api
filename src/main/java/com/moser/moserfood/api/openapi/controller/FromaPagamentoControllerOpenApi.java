package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.FormaPagamentoDTO;
import com.moser.moserfood.api.model.input.FormaPagamentoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Payment method")
public interface FromaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento")
    public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public ResponseEntity<FormaPagamentoDTO> buscar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId,
            ServletWebRequest request);

    @ApiOperation("Cadastra um forma de pagamento")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada"))
    public FormaPagamentoDTO salvar(@ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento")
                                        FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Atualiza um forma de pagamento por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "FormaPagamentoDTO atualizado"),
            @ApiResponse(responseCode = "404", description = "FormaPagamentoDTO não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public FormaPagamentoDTO atualizar(@ApiParam(value = "ID de uma forma de pagamento", example = "1")
                              Long formaPagamentoId,
                              @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
                              FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Exclui um forma de pagamento por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "FormaPagamentoDTO excluído"),
            @ApiResponse(responseCode = "404", description = "FormaPagamentoDTO não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void remover(@ApiParam(value = "ID de um formaPagamento", example = "1") Long formaPagamentoId);

}
