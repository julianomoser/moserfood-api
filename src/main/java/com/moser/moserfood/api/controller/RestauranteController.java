package com.moser.moserfood.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moser.moserfood.api.assembler.RestauranteDTOAssembler;
import com.moser.moserfood.api.assembler.RestauranteInputDisassembler;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.RestauranteDTO;
import com.moser.moserfood.api.model.input.RestauranteInput;
import com.moser.moserfood.api.model.view.RestauranteView;
import com.moser.moserfood.core.validation.ValidacaoException;
import com.moser.moserfood.domain.exception.CidadeNaoEncontradaException;
import com.moser.moserfood.domain.exception.CozinhaNaoEncontradaException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.exception.RestauranteNaoEncontradaException;
import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.RestauranteRepository;
import com.moser.moserfood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * @author Juliano Moser
 */
@Api(tags = "Restaurant")
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteDTOAssembler restauranteDTOAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @ApiOperation("Lista os restaurantes")
    @JsonView(RestauranteView.Resumo.class)
    @GetMapping()
    public List<RestauranteDTO> listar() {
        return restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
    }

    @ApiOperation("Lista somente os restaurantes")
    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteDTO> listarApenaNomes() {
        return listar();
    }

//    @GetMapping()
//    public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteDTO> restaurantesDTOs = restauranteDTOAssembler.toCollectionDTO(restaurantes);
//
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesDTOs);
//
//        restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//        if ("apenas-nome".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//        } else if ("completo".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(null);
//        }
//
//        return restaurantesWrapper;
//    }

//    @GetMapping()
//    public List<RestauranteDTO> listar() {
//        return restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
//    }
//
//    @JsonView(RestauranteView.Resumo.class)
//    @GetMapping(params = "projecao=resumo")
//    public List<RestauranteDTO> listarResumido() {
//        return listar();
//    }
//
//    @JsonView(RestauranteView.ApenasNome.class)
//    @GetMapping(params = "projecao=apenas-nome")
//    public List<RestauranteDTO> listarApenasNomes() {
//        return listar();
//    }

    @ApiOperation("Busca um restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da restaurante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.findOrFail(restauranteId);
        return restauranteDTOAssembler.toDTO(restaurante);
    }

    @ApiOperation("Cadastra um restaurante")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO salvar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDTOObject(restauranteInput);
            return restauranteDTOAssembler.toDTO(restauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Atualiza um restaurante por Id")
    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@PathVariable Long restauranteId,
                                    @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = restauranteService.findOrFail(restauranteId);

            restauranteInputDisassembler.copyToDTOObject(restauranteInput, restauranteAtual);

            return restauranteDTOAssembler.toDTO(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Ativar um restaurante por Id")
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @ApiOperation("Inativar um restaurante por Id")
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
    }

    @ApiOperation("Ativar múltiplos restaurantes por IDs")
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @ApiOperation("Inativar múltiplos restaurantes por IDs")
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @ApiOperation("Abrir um restaurantes por ID")
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
    }

    @ApiOperation("Fechar um restaurantes por ID")
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
    }


//    @PatchMapping("/{restauranteId}")
//    public RestauranteDTO atualizarParcial(@PathVariable Long restauranteId,
//                                        @RequestBody Map<String, Object> campos, HttpServletRequest request) {
//        Restaurante restauranteAtual = restauranteService.findOrFail(restauranteId);
//
//        merge(campos, restauranteAtual, request);
//        validate(restauranteAtual, "restaurante");
//        return atualizar(restauranteId, toModel(restauranteAtual));
//    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest servletHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
            dadosOrigem.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, key);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e.getCause());
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletHttpRequest);
        }
    }

    @ApiOperation("Exclui um restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do resrautante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        restauranteService.excluir(restauranteId);
    }

    @ApiOperation("Lista restaurantes por taxa frete")
    @GetMapping("/por-taxa-frete")
    public List<Restaurante> restaurantePorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @ApiOperation("Lista restaurantes por nome")
    @GetMapping("/por-nome")
    public List<Restaurante> restaurantePorNome(String nome, Long cozinhaId) {
        return restauranteRepository.consultByNome(nome, cozinhaId);
    }

    @ApiOperation("Lista primiero restaurante por nome")
    @GetMapping("/primeiro-por-nome")
    public Optional<Restaurante> restauranteFirstByNome(String nome) {
        return restauranteRepository.getFirstByNomeContaining(nome);
    }

    @ApiOperation("Lista os dois primeiros restaurantes")
    @GetMapping("/top2-por-nome")
    public List<Restaurante> restaurantesTopTwo(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @ApiOperation("Busca restaurante existente por nome")
    @GetMapping("/exists")
    public ResponseEntity<Restaurante> restauranteExistsByNome(String nome) {
        final boolean exists = restauranteRepository.existsByNome(nome);
        if (exists) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation("Conta cozinhas por Id")
    @GetMapping("/count-by-cozinha")
    public int restauranteCountByCozinha(Long conzinhaId) {
        return restauranteRepository.countByCozinhaId(conzinhaId);
    }

    @ApiOperation("Busca restaurante por nome e frete")
    @GetMapping("/count-by-nome-and-frete")
    public List<Restaurante> restauranteByNomeAndFrete(String nome,
                                                       BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @ApiOperation("Busca restaurante por frete grátis")
    @GetMapping("/count-by-free-shipping")
    public List<Restaurante> restauranteByFreeShipping(String name) {
        return restauranteRepository.findByFreeShipping(name);
    }

    @ApiOperation("Busca o primeiro restaurante")
    @GetMapping("/first")
    public Optional<Restaurante> firstRestaurante() {
        return restauranteRepository.findFirst();
    }

}
