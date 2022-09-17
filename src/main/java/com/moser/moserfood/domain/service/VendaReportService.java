package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.filter.VendaDiariaFilter;

/**
 * @author Juliano Moser
 */
public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
