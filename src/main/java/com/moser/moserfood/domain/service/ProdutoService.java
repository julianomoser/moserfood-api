package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.ProdutoNaoEncontradoException;
import com.moser.moserfood.domain.model.Produto;
import com.moser.moserfood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto findOrFail(Long resturanteId, Long produtoId) {
        return produtoRepository.findById(resturanteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(resturanteId, produtoId));
    }
}
