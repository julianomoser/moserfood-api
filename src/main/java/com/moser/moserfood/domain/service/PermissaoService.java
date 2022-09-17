package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.GrupoNaoEncontradoException;
import com.moser.moserfood.domain.model.Permissao;
import com.moser.moserfood.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    public Permissao findOrFail(Long grupoId) {
        return permissaoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
