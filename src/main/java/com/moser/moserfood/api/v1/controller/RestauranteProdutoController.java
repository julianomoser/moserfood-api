package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.assembler.ProdutoDTOAssembler;
import com.moser.moserfood.api.v1.assembler.ProdutoInputDisassembler;
import com.moser.moserfood.api.v1.model.ProdutoDTO;
import com.moser.moserfood.api.v1.model.input.ProdutoInput;
import com.moser.moserfood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.moser.moserfood.core.security.CheckSecurity;
import com.moser.moserfood.domain.model.Produto;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.ProdutoRepository;
import com.moser.moserfood.domain.service.ProdutoService;
import com.moser.moserfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private MoserLinks moserLinks;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<ProdutoDTO> listar(@PathVariable Long restauranteId,
                                              @RequestParam(required = false) Boolean incluirInativos) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);
        List<Produto> todosProtudos = null;

        if (incluirInativos) {
            todosProtudos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProtudos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoDTOAssembler.toCollectionModel(todosProtudos)
                .add(moserLinks.linkToProdutos(restauranteId));
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.findOrFail(restauranteId, produtoId);

        return produtoDTOAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO salvar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);
        return produtoDTOAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoService.findOrFail(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
        produto = produtoService.salvar(produto);

        return produtoDTOAssembler.toModel(produto);
    }
}
