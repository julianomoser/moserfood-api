package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.MoserLinks;
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
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private MoserLinks moserLinks;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();
        rootEntryPointModel.add(moserLinks.linkToCozinhas("cozinhas"));
        rootEntryPointModel.add(moserLinks.linkToPedidos("pedidos"));
        rootEntryPointModel.add(moserLinks.linkToRestaurantes("restaurantes"));
        rootEntryPointModel.add(moserLinks.linkToGrupos("grupos"));
        rootEntryPointModel.add(moserLinks.linkToUsuarios("usuarios"));
        rootEntryPointModel.add(moserLinks.linkToPermissoes("permissões"));
        rootEntryPointModel.add(moserLinks.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointModel.add(moserLinks.linkToEstados("estados"));
        rootEntryPointModel.add(moserLinks.linkToCidades("cidades"));
        return rootEntryPointModel;
    }
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
