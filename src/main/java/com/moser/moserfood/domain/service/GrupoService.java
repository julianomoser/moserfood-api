package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.EntidadeEmUsoException;
import com.moser.moserfood.domain.exception.GrupoNaoEncontradoException;
import com.moser.moserfood.domain.model.Grupo;
import com.moser.moserfood.domain.model.Permissao;
import com.moser.moserfood.domain.repository.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GrupoService {

    private static final String MSG_GRUPO_EM_USO
            = "Grupo de código %d não pode ser removido, pois está em uso";

    private final GrupoRepository grupoRepository;

    private final PermissaoService permissaoService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(grupoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_GRUPO_EM_USO, grupoId)
            );
        }
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = findOrFail(grupoId);

        Permissao permissao = permissaoService.findOrFail(permissaoId);

        grupo.adiocinarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = findOrFail(grupoId);

        Permissao permissao = permissaoService.findOrFail(permissaoId);

        grupo.removerPermissao(permissao);
    }

    public Grupo findOrFail(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
