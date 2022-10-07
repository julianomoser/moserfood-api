package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.UsuarioDTOAssembler;
import com.moser.moserfood.api.assembler.UsuarioInputDisassembler;
import com.moser.moserfood.api.model.UsuarioDTO;
import com.moser.moserfood.api.model.input.SenhaInput;
import com.moser.moserfood.api.model.input.UsuarioComSenhaInput;
import com.moser.moserfood.api.model.input.UsuarioInput;
import com.moser.moserfood.api.openapi.controller.UsuarioControllerOpenApi;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.repository.UsuarioRepository;
import com.moser.moserfood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDTO> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();

        return usuarioDTOAssembler.toCollectionDTO(todasUsuarios);
    }

    @GetMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findOrFail(usuarioId);

        return usuarioDTOAssembler.toDTO(usuario);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);

        usuario = usuarioService.salvar(usuario);

        return usuarioDTOAssembler.toDTO(usuario);
    }

    @PutMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = usuarioService.findOrFail(usuarioId);

        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);

        usuarioAtual = usuarioService.salvar(usuarioAtual);

        return usuarioDTOAssembler.toDTO(usuarioAtual);
    }

    @PutMapping(path = "/{usuarioId}/senha", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }
}
