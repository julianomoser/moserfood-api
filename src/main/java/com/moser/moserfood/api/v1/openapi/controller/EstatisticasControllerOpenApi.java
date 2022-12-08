package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.controller.EstatisticasController;
import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.moser.moserfood.api.v1.controller.EstatisticasController.*;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatísticas")
public interface EstatisticasControllerOpenApi {

    @Operation(hidden = true)
    EstatisticaDTO estatisticas();
    @Operation(summary = "Consulta estatísticas de vendas diárias")
    List<VendaDiaria> consultarVendasDiarias(
            @Parameter(hidden = true) VendaDiariaFilter filtro,
            @Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
                       schema = @Schema(type = "string", defaultValue = "+00:00"))
                       String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiasriasPdf(VendaDiariaFilter filtro, String timeOffset);
}
