package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.core.security.MoserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private MoserLinks moserLinks;
    @Autowired
    private MoserSecurity moserSecurity;

    @GetMapping
    @Operation(hidden = true)
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (moserSecurity.podeConsultarCozinhas()) {
            rootEntryPointModel.add(moserLinks.linkToCozinhas("cozinhas"));
        }

        if (moserSecurity.podePesquisarPedidos()) {
            rootEntryPointModel.add(moserLinks.linkToPedidos("pedidos"));
        }

        if (moserSecurity.podeConsultarRestaurantes()) {
            rootEntryPointModel.add(moserLinks.linkToRestaurantes("restaurantes"));
        }

        if (moserSecurity.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointModel.add(moserLinks.linkToGrupos("grupos"));
            rootEntryPointModel.add(moserLinks.linkToUsuarios("usuarios"));
            rootEntryPointModel.add(moserLinks.linkToPermissoes("permissões"));
        }

        if (moserSecurity.podeConsultarFormasPagamento()) {
            rootEntryPointModel.add(moserLinks.linkToFormasPagamento("formas-pagamento"));
        }

        if (moserSecurity.podeConsultarEstados()) {
            rootEntryPointModel.add(moserLinks.linkToEstados("estados"));
        }

        if (moserSecurity.podeConsultarCidades()) {
            rootEntryPointModel.add(moserLinks.linkToCidades("cidades"));
        }

        if (moserSecurity.podeConsultarEstatisticas()) {
            rootEntryPointModel.add(moserLinks.linkToEstatisticas("estatisticas"));
        }

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
