package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.UsuarioDTO;
import com.moser.moserfood.api.model.input.SenhaInput;
import com.moser.moserfood.api.model.input.UsuarioComSenhaInput;
import com.moser.moserfood.api.model.input.UsuarioInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@Api(tags = "User")
public interface UsuarioControllerOpenApi {

    @ApiOperation(value = "Lista os usuários")
    CollectionModel<UsuarioDTO> listar();


    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ID do usuário inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    UsuarioDTO buscar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                      Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    UsuarioDTO salvar(@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
                      UsuarioComSenhaInput usuarioInput);

    @ApiOperation("Atualiza um usuário por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    UsuarioDTO atualizar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                         Long usuarioId,
                         @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados",
                                 required = true)
                         UsuarioInput usuarioInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void alterarSenha(@ApiParam(value = "ID do usuário", example = "1", required = true)
                            Long usuarioId,
                            @ApiParam(name = "corpo", value = "Representação de uma nova senha",
                                    required = true)
                            SenhaInput senha);

    @ApiOperation("Exclui uma usuário por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do usuário inválida", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(@ApiParam(value = "ID de um usuário", example = "1", required = true)
                 Long usuarioId);
}
