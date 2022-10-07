package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Statistic")
public interface EstatisticasControllerOpenApi {

    @ApiOperation("Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
                    example = "1", dataType = "int"),
            @ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
                    example = "2019-12-01T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
                    example = "2019-12-02T23:59:59Z", dataType = "date-time")
    })
    List<VendaDiaria> consultarVendasDiasrias(VendaDiariaFilter filtro,
                                              @ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
                                                      defaultValue = "+00:00")
                                              String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiasriasPdf (
            VendaDiariaFilter filtro,
            String timeOffset);
}
