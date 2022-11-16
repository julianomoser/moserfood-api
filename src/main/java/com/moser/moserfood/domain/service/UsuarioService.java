package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.EntidadeEmUsoException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.exception.UsuarioNaoEncontradoException;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UsuarioService {

    private static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removida, pois está em uso ";

    private final UsuarioRepository usuarioRepository;

    private final GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {

        usuarioRepository.detach(usuario);

        Optional<Usuario> usuarioAtual = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioAtual.isPresent() && !usuarioAtual.get().getId().equals(usuario)) {
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com email %s", usuario.getEmail()));
        }

        if(usuario.isNovo()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = findOrFail(usuarioId);

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(passwordEncoder.encode(novaSenha));
    }

    @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_USUARIO_EM_USO, usuarioId)
            );
        }
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = findOrFail(usuarioId);
        Grupo grupo = grupoService.findOrFail(grupoId);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = findOrFail(usuarioId);
        Grupo grupo = grupoService.findOrFail(grupoId);

        usuario.removerGrupo(grupo);
    }

    public Usuario findOrFail(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
