package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.assembler.ProdutoDTOAssembler;
import com.moser.moserfood.api.assembler.ProdutoInputDisassembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.ProdutoDTO;
import com.moser.moserfood.api.model.input.ProdutoInput;
import com.moser.moserfood.domain.model.Produto;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.ProdutoRepository;
import com.moser.moserfood.domain.service.ProdutoService;
import com.moser.moserfood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Restaurant product")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

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

    @ApiOperation("Lista produtos de restaurante por Id")
    @GetMapping()
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId,
                                   @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);
        List<Produto> todosProtudos = null;

        if (incluirInativos) {
            todosProtudos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProtudos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoDTOAssembler.toCollectionDTO(todosProtudos);
    }

    @ApiOperation("Busca um produto de um restaurante por Id")
    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.findOrFail(restauranteId, produtoId);

        return produtoDTOAssembler.toDTO(produto);
    }

    @ApiOperation("Cadastra um produto em um restaurante por Id")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Produto cadastrada no restaurante"))
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO salvar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);
        return produtoDTOAssembler.toDTO(produto);
    }

    @ApiOperation("Atualiza um produto em um restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @PutMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoService.findOrFail(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
        produto = produtoService.salvar(produto);

        return produtoDTOAssembler.toDTO(produto);
    }
}
