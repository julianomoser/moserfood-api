package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.UsuarioDTO;
import com.moser.moserfood.api.v1.model.input.SenhaInput;
import com.moser.moserfood.api.v1.model.input.UsuarioComSenhaInput;
import com.moser.moserfood.api.v1.model.input.UsuarioInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuarioControllerOpenApi {

    @Operation(summary = "Lista os usuários")
    CollectionModel<UsuarioDTO> listar();

    @Operation(summary = "Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ID do usuário inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    UsuarioDTO buscar(Long usuarioId);

    @Operation(summary = "Cadastra um usuário")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    UsuarioDTO salvar(UsuarioComSenhaInput usuarioInput);

    @Operation(summary = "Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    UsuarioDTO atualizar(Long usuarioId, UsuarioInput usuarioInput);

    @Operation(summary = "Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void alterarSenha(Long usuarioId, SenhaInput senha);

    @Operation(summary = "Exclui um usuário por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do usuário inválida", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long usuarioId);
}
