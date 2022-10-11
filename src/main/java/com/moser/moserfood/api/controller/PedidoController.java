package com.moser.moserfood.api.controller;

import com.google.common.collect.ImmutableMap;
import com.moser.moserfood.api.assembler.PedidoDTOAssembler;
import com.moser.moserfood.api.assembler.PedidoInputDisassembler;
import com.moser.moserfood.api.assembler.PedidoResumoDTOAssembler;
import com.moser.moserfood.api.model.PedidoDTO;
import com.moser.moserfood.api.model.PedidoResumoDTO;
import com.moser.moserfood.api.model.input.PedidoInput;
import com.moser.moserfood.api.openapi.controller.PedidoControllerOpenApi;
import com.moser.moserfood.core.data.PageableTranslator;
import com.moser.moserfood.domain.exception.EntidadeNaoEncontradaException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.filter.PedidoFilter;
import com.moser.moserfood.domain.model.Pedido;
import com.moser.moserfood.domain.model.Usuario;
import com.moser.moserfood.domain.repository.PedidoRepository;
import com.moser.moserfood.domain.service.EmissaoPedidoService;
import com.moser.moserfood.infrastructure.repository.spec.PedidoSpecs;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Order")
@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro,
                                                 @PageableDefault(10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usingFilter(filtro), pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoDTOAssembler);
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


//    @GetMapping
//    public List<PedidoResumoDTO> listar() {
//        List<Pedido> todasPedidos = PedidoRepository.findAll();
//
//        return pedidoResumoDTOAssembler.toCollectionModel(todasPedidos);
//    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            //TODO pegar usuário autenticado
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            pedido = emissaoPedidoService.emitir(pedido);

            return pedidoDTOAssembler.toModel(pedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @GetMapping(path = "/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.findOrFail(codigoPedido);

        return pedidoDTOAssembler.toModel(pedido);
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
