package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.EntidadeEmUsoException;
import com.moser.moserfood.domain.exception.EstadoNaoEncontradoException;
import com.moser.moserfood.domain.model.Estado;
import com.moser.moserfood.domain.repository.EstadoRepository;
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
public class EstadoService {

    private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso ";

    private final EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId)
            );
        }
    }

    public Estado findOrFail(Long esdadoId) {
        return estadoRepository.findById(esdadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(esdadoId));
    }
}
