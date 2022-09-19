package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.UsuarioDTOAssembler;
import com.moser.moserfood.api.assembler.UsuarioInputDisassembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.UsuarioDTO;
import com.moser.moserfood.api.model.input.SenhaInput;
import com.moser.moserfood.api.model.input.UsuarioComSenhaInput;
import com.moser.moserfood.api.model.input.UsuarioInput;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.repository.UsuarioRepository;
import com.moser.moserfood.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "User")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @ApiOperation("Lista os usuários")
    @GetMapping
    public List<UsuarioDTO> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();

        return usuarioDTOAssembler.toCollectionDTO(todasUsuarios);
    }

    @ApiOperation("Busca uma usuário por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findOrFail(usuarioId);

        return usuarioDTOAssembler.toDTO(usuario);
    }

    @ApiOperation("Cadastra um usuário")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);

        usuario = usuarioService.salvar(usuario);

        return usuarioDTOAssembler.toDTO(usuario);
    }

    @ApiOperation("Atualiza um usuário por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = usuarioService.findOrFail(usuarioId);

        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);

        usuarioAtual = usuarioService.salvar(usuarioAtual);

        return usuarioDTOAssembler.toDTO(usuarioAtual);
    }

    @ApiOperation("Atualizar senha")
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

    @ApiOperation("Exclui um usuário por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }
}
