package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.FotoProdutoNaoEncontradaException;
import com.moser.moserfood.domain.model.FotoProduto;
import com.moser.moserfood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

import static com.moser.moserfood.domain.service.FotoStorageService.NovaFoto;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CatalagoFotoProdutoService {

    private ProdutoRepository produtoRepository;

    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String novoNomeArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente =
                produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(novoNomeArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .contentType(foto.getContentType())
                .inputStream(dadosArquivo)
                .build();

        fotoStorage.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }


    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {

        FotoProduto foto = findOrFail(restauranteId, produtoId);

        produtoRepository.delete(foto);
        fotoStorage.remover(foto.getNomeArquivo());
    }

    public FotoProduto findOrFail(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
    }
}
