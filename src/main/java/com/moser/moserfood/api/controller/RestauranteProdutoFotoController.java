package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.FotoProdutoDTOAssembler;
import com.moser.moserfood.api.model.FotoProdutoDTO;
import com.moser.moserfood.api.model.input.FotoProdutoInput;
import com.moser.moserfood.domain.exception.EntidadeNaoEncontradaException;
import com.moser.moserfood.domain.model.FotoProduto;
import com.moser.moserfood.domain.model.Produto;
import com.moser.moserfood.domain.service.CatalagoFotoProdutoService;
import com.moser.moserfood.domain.service.FotoStorageService;
import com.moser.moserfood.domain.service.FotoStorageService.FotoRecuperada;
import com.moser.moserfood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalagoFotoProdutoService catalagoFotoProdutoService;

    @Autowired
    private FotoStorageService fotoStorageService;


    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
        Produto produto = produtoService.findOrFail(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = createFotoProduto(fotoProdutoInput, produto, arquivo);

        return fotoProdutoDTOAssembler.toDTO(catalagoFotoProdutoService.salvar(foto, arquivo.getInputStream()));
    }

    private FotoProduto createFotoProduto(FotoProdutoInput fotoProdutoInput, Produto produto, MultipartFile arquivo) {
        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        return foto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId,
                                 @PathVariable Long produtoId) {
        FotoProduto foto = catalagoFotoProdutoService.findOrFail(restauranteId, produtoId);
        return fotoProdutoDTOAssembler.toDTO(foto);
    }

    @GetMapping
    public ResponseEntity<?> servir(@PathVariable Long restauranteId,
                                                      @PathVariable Long produtoId,
                                                      @RequestHeader(name = "accept") String accetpHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto fotoProduto = catalagoFotoProdutoService.findOrFail(restauranteId, produtoId);


            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(accetpHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
            if (fotoRecuperada.temUrl()) {
                return ResponseEntity.status(HttpStatus.FOUND).
                        header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
                                                   List<MediaType> mediaTypesAceitas)
            throws HttpMediaTypeNotAcceptableException {
        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId,
                       @PathVariable Long produtoId) {
        catalagoFotoProdutoService.excluir(restauranteId, produtoId);
    }

}
