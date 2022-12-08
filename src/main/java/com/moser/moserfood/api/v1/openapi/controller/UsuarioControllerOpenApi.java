package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.UsuarioDTO;
import com.moser.moserfood.api.v1.model.input.SenhaInput;
import com.moser.moserfood.api.v1.model.input.UsuarioComSenhaInput;
import com.moser.moserfood.api.v1.model.input.UsuarioInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    @Operation(summary = "Busca um usuário por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = {@Content(schema =
            @Schema(ref = "Problema"))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {@Content(schema =
            @Schema(ref = "Problema"))}),
    })
    UsuarioDTO buscar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

    @Operation(summary = "Cadastra um usuário")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    UsuarioDTO salvar(@RequestBody(description = "Representação de um novo usuário", required = true)
                      UsuarioComSenhaInput usuarioInput);

    @Operation(summary = "Atualiza um usuário por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {@Content(schema =
            @Schema(ref = "Problema"))})
    })
    UsuarioDTO atualizar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
                         @RequestBody(description = "Representação de um usuário com os novos dados", required = true)
                         UsuarioInput usuarioInput);

    @Operation(summary = "Atualiza a senha de um usuário", responses = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {@Content(schema =
            @Schema(ref = "Problema"))})
    })
    void alterarSenha(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
                      @RequestBody(description = "Representação de uma nova senha", required = true) SenhaInput senha);

    @Operation(summary = "Exclui um usuário por Id", responses = {
            @ApiResponse(responseCode = "400", description = "ID do usuário inválida", content = @Content(schema =
            @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrada", content = @Content(schema =
            @Schema(ref = "Problema")))
    })
    void remover(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
}
