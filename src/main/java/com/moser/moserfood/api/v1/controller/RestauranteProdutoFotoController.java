package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.assembler.FotoProdutoDTOAssembler;
import com.moser.moserfood.api.v1.model.FotoProdutoDTO;
import com.moser.moserfood.api.v1.model.input.FotoProdutoInput;
import com.moser.moserfood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.moser.moserfood.core.security.CheckSecurity;
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
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

    @Autowired
    private CatalagoFotoProdutoService catalagoFotoProdutoService;

    @Autowired
    private FotoStorageService fotoStorageService;


    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput,
                                        @RequestPart(required = true) MultipartFile arquivo) throws IOException {
        Produto produto = produtoService.findOrFail(restauranteId, produtoId);

//        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = createFotoProduto(fotoProdutoInput, produto, arquivo);

        return fotoProdutoDTOAssembler.toModel(catalagoFotoProdutoService.salvar(foto, arquivo.getInputStream()));
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

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId,
                                 @PathVariable Long produtoId) {
        FotoProduto foto = catalagoFotoProdutoService.findOrFail(restauranteId, produtoId);
        return fotoProdutoDTOAssembler.toModel(foto);
    }

    @GetMapping()
    public ResponseEntity<?> servir(@PathVariable Long restauranteId,
                                    @PathVariable Long produtoId,
                                    @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        if (acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
            return recuperarFoto(restauranteId, produtoId);
        }
        try {
            FotoProduto fotoProduto = catalagoFotoProdutoService.findOrFail(restauranteId, produtoId);


            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

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

    public ResponseEntity<?> recuperarFoto(@PathVariable Long restauranteId,@PathVariable Long produtoId)  {
        FotoProdutoDTO fotoProdutoModel = fotoProdutoDTOAssembler.toModel(catalagoFotoProdutoService.findOrFail(restauranteId, produtoId));
        return ResponseEntity.ok(fotoProdutoModel);
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

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId,
                       @PathVariable Long produtoId) {
        catalagoFotoProdutoService.excluir(restauranteId, produtoId);
    }

}
