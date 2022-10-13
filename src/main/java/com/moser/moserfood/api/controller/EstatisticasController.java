package com.moser.moserfood.api.controller;

import com.moser.moserfood.api.MoserLinks;
import com.moser.moserfood.api.openapi.controller.EstatisticasControllerOpenApi;
import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import com.moser.moserfood.domain.service.VendaQueryService;
import com.moser.moserfood.infrastructure.service.report.PdfVendaReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private PdfVendaReportService pdfVendaReportService;

    @Autowired
    private MoserLinks moserLinks;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticaDTO estatisticas() {
        var estatisticas = new EstatisticaDTO();
        estatisticas.add(moserLinks.linkToEstatisticas("vendas-diárias"));
        return estatisticas;
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filter, timeOffset);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiasriasPdf(VendaDiariaFilter filter,
             @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        byte[] bytesPdf = pdfVendaReportService.emitirVendasDiarias(filter, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    private static class EstatisticaDTO extends RepresentationModel<EstatisticaDTO> {
    }
}
