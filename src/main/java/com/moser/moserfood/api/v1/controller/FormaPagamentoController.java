package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.assembler.FormaPagamentoDTOAssembler;
import com.moser.moserfood.api.v1.assembler.FormaPagamentoInputDisassembler;
import com.moser.moserfood.api.v1.model.FormaPagamentoDTO;
import com.moser.moserfood.api.v1.model.input.FormaPagamentoInput;
import com.moser.moserfood.api.v1.openapi.controller.FormaPagamentoControllerOpenApi;
import com.moser.moserfood.core.security.CheckSecurity;
import com.moser.moserfood.domain.exception.EstadoNaoEncontradoException;
import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.model.FormaPagamento;
import com.moser.moserfood.domain.repository.FormaPagamentoRepository;
import com.moser.moserfood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @CheckSecurity.FormasPagamento.PodeConsultar
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        String eTag = getETag(dataUltimaAtualizacao);

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> todasFormaPagamentos = formaPagamentoRepository.findAll();

        CollectionModel<FormaPagamentoDTO> formasPagamentoDTO = formaPagamentoModelAssembler.toCollectionModel(todasFormaPagamentos);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentoDTO);
    }

    @CheckSecurity.FormasPagamento.PodeConsultar
    @Override
    @GetMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId,
                                                    ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacaoById(formaPagamentoId);

        String eTag = getETag(dataUltimaAtualizacao);

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamento formaPagamento = formaPagamentoService.findOrFail(formaPagamentoId);
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoModelAssembler.toModel(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoDTO);
    }

    private String getETag(OffsetDateTime dataUltimaAtualizacao) {
        String eTag = "0";

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }
        return eTag;
    }

    @CheckSecurity.FormasPagamento.PodeEditar
    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        try {
            FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

            formaPagamento = formaPagamentoService.salvar(formaPagamento);

            return formaPagamentoModelAssembler.toModel(formaPagamento);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.FormasPagamento.PodeEditar
    @Override
    @PutMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
                                       @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        try {
            FormaPagamento formaPagamentoAtual = formaPagamentoService.findOrFail(formaPagamentoId);

            formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

            formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

            return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.FormasPagamento.PodeEditar
    @Override
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
