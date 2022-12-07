package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Juliano Moser
 */
public interface EstatisticasControllerOpenApi {


    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiasriasPdf(
            VendaDiariaFilter filtro,
            String timeOffset);
}
