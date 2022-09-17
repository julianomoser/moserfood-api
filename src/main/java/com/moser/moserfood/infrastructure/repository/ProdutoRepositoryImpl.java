package com.moser.moserfood.infrastructure.repository;

import com.moser.moserfood.domain.model.FotoProduto;
import com.moser.moserfood.domain.repository.ProdutoRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Juliano Moser
 */
@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return entityManager.merge(foto);
    }

    @Transactional
    @Override
    public void delete(FotoProduto foto) {
        entityManager.remove(foto);
    }
}
