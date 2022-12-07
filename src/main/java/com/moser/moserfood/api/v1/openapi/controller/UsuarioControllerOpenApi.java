package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.UsuarioDTO;
import com.moser.moserfood.api.v1.model.input.SenhaInput;
import com.moser.moserfood.api.v1.model.input.UsuarioComSenhaInput;
import com.moser.moserfood.api.v1.model.input.UsuarioInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
public interface UsuarioControllerOpenApi {

    CollectionModel<UsuarioDTO> listar();


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ID do usuário inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    UsuarioDTO buscar(Long usuarioId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    UsuarioDTO salvar(UsuarioComSenhaInput usuarioInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    UsuarioDTO atualizar(Long usuarioId, UsuarioInput usuarioInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void alterarSenha(Long usuarioId, SenhaInput senha);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do usuário inválida", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long usuarioId);
}
