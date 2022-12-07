package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatísticas")
public interface EstatisticasControllerOpenApi {


    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiasriasPdf(
            VendaDiariaFilter filtro,
            String timeOffset);
}
