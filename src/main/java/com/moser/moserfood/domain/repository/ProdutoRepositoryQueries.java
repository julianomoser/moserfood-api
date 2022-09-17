package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.FotoProduto;

/**
 * @author Juliano Moser
 */
public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);
}
