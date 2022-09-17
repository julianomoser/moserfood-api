package com.moser.moserfood.infrastructure.service.report;

import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import com.moser.moserfood.domain.service.VendaQueryService;
import com.moser.moserfood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * @author Juliano Moser
 */
@Service
public class PdfVendaReportService implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset) {
        try {
            List<VendaDiaria> vendaDiariaList = vendaQueryService.consultarVendasDiarias(filter, timeOffset);

            var param = new HashMap<String, Object>();

            param.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

            var jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper"));
            var dataSource = new JRBeanCollectionDataSource(vendaDiariaList, false);
            var jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e.getCause());
        }
    }
}
