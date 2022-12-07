package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.RestauranteApenasNomeDTO;
import com.moser.moserfood.api.v1.model.RestauranteBasicoDTO;
import com.moser.moserfood.api.v1.model.RestauranteDTO;
import com.moser.moserfood.api.v1.model.input.RestauranteInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteControllerOpenApi {

    CollectionModel<RestauranteBasicoDTO> listar();

    CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes();


    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    RestauranteDTO buscar(Long restauranteId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Restaurante cadastrada"))
    RestauranteDTO salvar(RestauranteInput restauranteInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante atualizada"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    RestauranteDTO atualizar(Long restauranteId, RestauranteInput restauranteInput);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do resrautante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long restauranteId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> ativar(Long restauranteId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> inativar(Long restauranteId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
    })
    void ativarMultiplos(List<Long> restauranteIds);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurantes inativados com sucesso")
    })
    void inativarMultiplos(List<Long> restauranteIds);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> abrir(Long restauranteId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> fechar(Long restauranteId);
}
