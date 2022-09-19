package com.moser.moserfood.api.controller;

import com.google.common.collect.ImmutableMap;
import com.moser.moserfood.api.assembler.PedidoDTOAssembler;
import com.moser.moserfood.api.assembler.PedidoInputDisassembler;
import com.moser.moserfood.api.assembler.PedidoResumoDTOAssembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.PedidoDTO;
import com.moser.moserfood.api.model.PedidoResumoDTO;
import com.moser.moserfood.api.model.input.PedidoInput;
import com.moser.moserfood.core.data.PageableTranslator;
import com.moser.moserfood.domain.exception.EntidadeNaoEncontradaException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.model.Pedido;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.repository.PedidoRepository;
import com.moser.moserfood.domain.filter.PedidoFilter;
import com.moser.moserfood.domain.service.EmissaoPedidoService;
import com.moser.moserfood.infrastructure.repository.spec.PedidoSpecs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Order")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(PedidoFilter filtro,
                                           @PageableDefault(10) Pageable pageable) {

        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usingFilter(filtro), pageable);

        List<PedidoResumoDTO> pedidosResumoDTO = pedidoResumoDTOAssembler.toCollectionDTO(pedidosPage.getContent());

        return new PageImpl<>(pedidosResumoDTO, pageable, pedidosPage.getTotalElements());
    }

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> todosPedidos = PedidoRepository.findAll();
//        List<PedidoResumoDTO> pedidosDTO = pedidoResumoDTOAssembler.toCollectionModel(todosPedidos);
//
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDTO);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if (StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter",
//                    SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        pedidosWrapper.setFilters(filterProvider);
//
//        return pedidosWrapper;
//    }

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

//    @GetMapping
//    public List<PedidoResumoDTO> listar() {
//        List<Pedido> todasPedidos = PedidoRepository.findAll();
//
//        return pedidoResumoDTOAssembler.toCollectionModel(todasPedidos);
//    }

    @ApiOperation("Adiciona um pedido")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Pedido cadastrado"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            //TODO pegar usuário autenticado
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            pedido = emissaoPedidoService.emitir(pedido);

            return pedidoDTOAssembler.toDTO(pedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @ApiOperation("Busca um pedido por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do pedido inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.findOrFail(codigoPedido);

        return pedidoDTOAssembler.toDTO(pedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
