package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.EntidadeEmUsoException;
import com.moser.moserfood.domain.exception.RestauranteNaoEncontradaException;
import com.moser.moserfood.domain.model.*;
import com.moser.moserfood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso ";

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;
    private final CidadeService cidadeService;

    private final FormaPagamentoService formaPagamentoService;

    private final UsuarioService usuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cozinhaService.findOrFail(cozinhaId);
        Cidade cidade = cidadeService.findOrFail(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void excluir(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradaException(restauranteId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_RESTAURANTE_EM_USO, restauranteId)
            );
        }
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = findOrFail(restauranteId);

        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = findOrFail(restauranteId);

        restaurante.inativar();
    }

    @Transactional
    public void ativar(List<Long> resturanteIds) {
        resturanteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> resturanteIds) {
        resturanteIds.forEach(this::inativar);
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = findOrFail(restauranteId);

        restaurante.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = findOrFail(restauranteId);

        restaurante.fechar();
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findOrFail(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.findOrFail(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
        // Não previsa chamar o save, pois já está dentro de um transactional e o JPA irá sincronizar
        // essas informações com o BD

    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findOrFail(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.findOrFail(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
        // Não previsa chamar o save, pois já está dentro de um transactional e o JPA irá sincronizar
        // essas informações com o BD

    }

    @Transactional
    public void associarResponsavel(Long restauranreId, Long usuarioId) {
        Restaurante restaurante = findOrFail(restauranreId);

        Usuario usuario = usuarioService.findOrFail(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

    @Transactional
    public void desassociarResponsavel(Long restauranreId, Long usuarioId) {
        Restaurante restaurante = findOrFail(restauranreId);

        Usuario usuario = usuarioService.findOrFail(usuarioId);

        restaurante.removerResponsavel(usuario);
    }


    public Restaurante findOrFail(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradaException(restauranteId));
    }

}
