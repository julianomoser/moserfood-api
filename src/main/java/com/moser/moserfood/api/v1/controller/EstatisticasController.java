package com.moser.moserfood.api.v1.controller;

import com.moser.moserfood.api.v1.MoserLinks;
import com.moser.moserfood.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import com.moser.moserfood.core.security.CheckSecurity;
import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import com.moser.moserfood.domain.service.VendaQueryService;
import com.moser.moserfood.infrastructure.service.report.PdfVendaReportService;
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
@RequestMapping("/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private PdfVendaReportService pdfVendaReportService;

    @Autowired
    private MoserLinks moserLinks;


    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticaDTO estatisticas() {
        var estatisticas = new EstatisticaDTO();
        estatisticas.add(moserLinks.linkToEstatisticasVendasDiarias("vendas-diárias"));
        return estatisticas;
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filter, timeOffset);
    }

    @CheckSecurity.Estatisticas.PodeConsultar
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

    public static class EstatisticaDTO extends RepresentationModel<EstatisticaDTO> {
    }
}
